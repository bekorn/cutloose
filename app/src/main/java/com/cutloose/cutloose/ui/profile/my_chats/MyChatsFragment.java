package com.cutloose.cutloose.ui.profile.my_chats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.ui.chat.ChatActivity;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.BaseFragment;

/**
 * Created by finge on 5/6/2018.
 */

public class MyChatsFragment extends BaseFragment {
    MyChatsRecyclerViewModel mChatsFragmentViewModel;
    MyChatsRecyclerViewAdapter mChatsRecyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.my_chats_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mChatsFragmentViewModel = ViewModelProviders.of(this).get(MyChatsRecyclerViewModel.class);

        mChatsFragmentViewModel.fetchData(); //TODO: This should take in chat ID.

        adaptRecyclerView( view );
    }

    private void adaptRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.my_chat_recycler);

        mChatsRecyclerViewAdapter = new MyChatsRecyclerViewAdapter( mChatsFragmentViewModel, this );

        mChatsFragmentViewModel.observeAction( this, new Observer<Action<Chat, BasicAction>>() {
            @Override
            public void onChanged( @Nullable Action<Chat, BasicAction> chatAction ) {

                if( chatAction == null ) return;

                switch( chatAction.getActionType() ) {
                    case RECYCLER_ITEM_CLICK:
                        Intent intent = new Intent( getContext(), ChatActivity.class );
                        intent.putExtra( "isProfile", true );
                        intent.putExtra( "owners", chatAction.getModel().showOwners() );
                        startActivity( intent );
                        break;
                }
            }
        } );

        recyclerView.setAdapter( mChatsRecyclerViewAdapter );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
