package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.ChatFragmentBinding;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.BaseFragment;

import java.util.ArrayList;

public class ChatFragment extends BaseFragment implements View.OnLayoutChangeListener {

    private ChatFragmentRecyclerViewAdapter mChatFragmentRecyclerViewAdapter;
    private ChatFragmentRecyclerViewModel mChatFragmentRecyclerViewModel;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.chat_fragment;
    }

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState ) {

        mChatFragmentRecyclerViewModel = ViewModelProviders.of( this ).get( ChatFragmentRecyclerViewModel.class );

        ((ChatFragmentBinding)mViewDataBinding).setViewModel( mChatFragmentRecyclerViewModel );

        adaptRecyclerView( view );

        mChatFragmentRecyclerViewModel.getLiveData().observe( this, new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged( @Nullable ArrayList<Message> messages ) {
                if(messages == null || messages.size() == 0) return;
                mRecyclerView.smoothScrollToPosition( mChatFragmentRecyclerViewAdapter.getItemCount() );
            }
        } );

        listenActions();
    }

    public void listenActions() {
        mChatFragmentRecyclerViewModel.observeAction(this, new Observer<Action<Message, BasicAction>>() {
            @Override
            public void onChanged(@Nullable Action<Message, BasicAction> messageBasicActionAction) {
                if(messageBasicActionAction != null) {
                    if(messageBasicActionAction.getActionType() == BasicAction.ON_BUTTON_CLICK) {
                        ((ChatActivity) getActivity()).mChatActivityViewModel.showUsers.set(true);
                        ((ChatActivity) getActivity()).mChatActivityViewModel.mSearching.set(true);
                    }
                }
            }
        });
    }

    public void fetchData(String eventId, String chatId) {
        mChatFragmentRecyclerViewModel.fetchData(eventId, chatId);
    }

    private void adaptRecyclerView( View view ) {
        mRecyclerView = view.findViewById( R.id.chat_recycler );

        mRecyclerView.addOnLayoutChangeListener( this );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
        mRecyclerView.setLayoutManager( linearLayoutManager );

        mChatFragmentRecyclerViewAdapter = new ChatFragmentRecyclerViewAdapter( mChatFragmentRecyclerViewModel,this );
        mRecyclerView.setAdapter( mChatFragmentRecyclerViewAdapter );
    }

    @Override
    public void onLayoutChange( View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7 ) {
        /**
         * When keyboard opens, this makes chat scroll again to bottom.
         */
        mRecyclerView.smoothScrollToPosition( mChatFragmentRecyclerViewAdapter.getItemCount() );
    }
}
