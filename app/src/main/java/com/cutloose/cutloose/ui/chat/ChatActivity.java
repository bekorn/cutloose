package com.cutloose.cutloose.ui.chat;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.ChatActivityBinding;
import com.cutloose.cutloose.ui.common.BaseActivity;

public class ChatActivity extends BaseActivity {

    ChatActivityBinding mChatActivityBinding;
    ChatActivityViewModel mChatActivityViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.chat_activity;
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        mChatActivityViewModel = ViewModelProviders.of( this ).get( ChatActivityViewModel.class );

        mChatActivityBinding = (ChatActivityBinding) mViewDataBinding;
        mChatActivityBinding.setViewModel( mChatActivityViewModel );

        boolean isProfile = getIntent().getBooleanExtra( "isProfile", false );

        if( isProfile ) {
            mChatActivityViewModel.mSearching.set( false );
            setTitle( getIntent().getStringExtra( "owners" ) );
        } else {
            mChatActivityViewModel.mSearching.set( true );
            setFakeTimer();
        }
    }

    private void setFakeTimer() {
        new CountDownTimer( 3000, 1000 ) {

            public void onTick( long millisUntilFinished ) {
            }

            public void onFinish() {
                mChatActivityViewModel.mSearching.set( false );
            }

        }.start();
    }
}
