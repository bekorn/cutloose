package com.cutloose.cutloose.ui.chat.chat_users;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ViewDataBinding;

import com.cutloose.cutloose.BR;
import com.cutloose.cutloose.R;
import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewAdapter;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class ChatUsersRecyclerAdapter extends BaseRecyclerViewAdapter<Profile, BasicAction> {

    public ChatUsersRecyclerAdapter(BaseRecyclerViewModel<Profile, BasicAction> baseRecyclerViewModel, LifecycleOwner lifecycleOwner ) {
        super( baseRecyclerViewModel, lifecycleOwner );
    }

    @Override
    protected void setViewHolderBindings(ViewDataBinding binding, Profile message ) {
        binding.setVariable( BR.viewModel, new ChatUserItemViewModel( message ) );
    }

    @Override
    protected int getItemViewId() {
        return R.layout.chat_user_item;
    }
}