package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ViewDataBinding;

import com.cutloose.cutloose.BR;
import com.cutloose.cutloose.R;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewAdapter;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;


public class ChatFragmentRecyclerViewAdapter extends BaseRecyclerViewAdapter<Message, BasicAction> {

    public ChatFragmentRecyclerViewAdapter(BaseRecyclerViewModel<Message, BasicAction> baseRecyclerViewModel, LifecycleOwner lifecycleOwner) {
        super(baseRecyclerViewModel, lifecycleOwner);
    }

    @Override
    protected void setViewHolderBindings(ViewDataBinding binding, Message message) {
        binding.setVariable(BR.viewModel, new MessageViewModel(message));
    }

    @Override
    protected int getItemViewId() {
        return R.layout.chat_message_item;
    }
}