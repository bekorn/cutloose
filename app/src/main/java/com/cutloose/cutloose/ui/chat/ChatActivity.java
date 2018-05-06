package com.cutloose.cutloose.ui.chat;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.ChatActivityBinding;
import com.cutloose.cutloose.ui.common.BaseActivity;

public class ChatActivity extends BaseActivity {

    ChatActivityBinding chatActivityBinding;
    ChatActivityViewModel chatActivityViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatActivityViewModel = ViewModelProviders.of(this).get(ChatActivityViewModel.class);

        chatActivityBinding = (ChatActivityBinding) binding;
        chatActivityBinding.setViewModel(chatActivityViewModel);
        chatActivityViewModel.searching.set(true);
        setFakeTimer();
    }

    private void setFakeTimer() {
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                chatActivityViewModel.searching.set(false);
            }

        }.start();
    }

    @Override
    protected int getViewId() {
        return R.layout.chat_activity;
    }
}
