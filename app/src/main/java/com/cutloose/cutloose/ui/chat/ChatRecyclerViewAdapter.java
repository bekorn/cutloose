package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ViewDataBinding;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.BR;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewAdapter;

import java.util.ArrayList;

public class ChatRecyclerViewAdapter extends BaseRecyclerViewAdapter<Message> {

    public ChatRecyclerViewAdapter( MutableLiveData<ArrayList<Message>> observableData, LifecycleOwner lifecycleOwner ) {
        super( observableData, lifecycleOwner );
    }

    @Override
    protected void viewHolderBinder( ViewDataBinding binding, Message message ) {
        binding.setVariable( BR.viewModel, new MessageViewModel( message ) );
    }

    @Override
    protected int getItemViewId() {
        return R.layout.chat_message_item;
    }
}