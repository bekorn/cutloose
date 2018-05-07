package com.cutloose.cutloose.ui.profile;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.MyChatsFragmentBinding;
import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.ui.chat.ChatActivity;

/**
 * Created by finge on 5/6/2018.
 */

public class MyChatsFragment extends Fragment {
    MyChatsFragmentBinding myChatsFragmentBinding;
    MyChatsFragmentViewModel myChatsFragmentViewModel;
    MyChatsRecyclerViewAdapter myChatsRecyclerViewAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myChatsFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.my_chats_fragment,
                container,
                false
        );

        return myChatsFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myChatsFragmentViewModel = ViewModelProviders.of(this).get(MyChatsFragmentViewModel.class);

        myChatsFragmentViewModel.fetchData(); //TODO: This should take in chat ID.

        adaptRecyclerView( view );
    }

    private void adaptRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.my_chat_recycler);

        myChatsRecyclerViewAdapter = new MyChatsRecyclerViewAdapter( myChatsFragmentViewModel, this );

        myChatsRecyclerViewAdapter.mOnItemClickEvent.observe( this, new Observer<Chat>() {
            @Override
            public void onChanged( @Nullable Chat chat ) {

                Intent intent = new Intent( getContext(), ChatActivity.class );
                intent.putExtra( "isProfile", true );
                intent.putExtra( "owners", chat.showOwners() );
                startActivity( intent );
            }
        } );

        recyclerView.setAdapter( myChatsRecyclerViewAdapter );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
