package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import com.cutloose.cutloose.databinding.ChatFragmentBinding;
import com.cutloose.cutloose.model.Message;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements View.OnLayoutChangeListener {

    private ChatFragmentBinding mChatFragmentBinding;
    private ChatRecyclerViewAdapter mChatRecyclerViewAdapter;
    private ChatViewModel mChatViewModel;
    private RecyclerView mRecyclerView;

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        mChatViewModel = ViewModelProviders.of( this ).get( ChatViewModel.class );
        mChatFragmentBinding.setViewModel( mChatViewModel );

        mChatRecyclerViewAdapter = new ChatRecyclerViewAdapter( mChatViewModel.getMessages(), this );

        adaptRecyclerView( view );

        mChatViewModel.getMessages().observe( this, new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged( @Nullable ArrayList<Message> messages ) {
                mRecyclerView.smoothScrollToPosition( mChatRecyclerViewAdapter.getItemCount() );
            }
        } );

        mChatViewModel.fetchData(); //TODO: This should take in chat ID.
    }

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        mChatFragmentBinding = DataBindingUtil.inflate( inflater, R.layout.chat_fragment, container, false );
        return mChatFragmentBinding.getRoot();
    }

    private void adaptRecyclerView( View view ) {
        mRecyclerView = view.findViewById( R.id.chat_recycler );
        mRecyclerView.addOnLayoutChangeListener( this );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
        mRecyclerView.setLayoutManager( linearLayoutManager );
        mRecyclerView.setAdapter( mChatRecyclerViewAdapter );
    }

    @Override
    public void onLayoutChange( View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7 ) {
        /**
         * When keyboard opens, this makes chat scroll again to bottom.
         */
        mRecyclerView.smoothScrollToPosition( mChatRecyclerViewAdapter.getItemCount() );
    }
}
