package com.cutloose.cutloose.ui.chat.chat_users;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.ChatUsersFragmentBinding;
import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.ui.chat.ChatActivity;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.BaseFragment;

import java.util.ArrayList;

public class ChatUsersFragment extends BaseFragment {
    private ChatUsersRecyclerAdapter chatUsersRecyclerAdapter;
    private RecyclerView recyclerView;
    private ChatUsersRecyclerViewModel chatUsersRecyclerViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.chatUsersRecyclerViewModel = ViewModelProviders.of(this).get(ChatUsersRecyclerViewModel.class);
        ((ChatUsersFragmentBinding) mViewDataBinding).setViewModel(chatUsersRecyclerViewModel);
        adaptRecyclerView(view);
        listenForChanges();
        listenActions();
    }

    public void listenForChanges() {
        chatUsersRecyclerViewModel.getUsers().observe(this, new Observer<ArrayList<Profile>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Profile> profiles) {
                chatUsersRecyclerAdapter.setData(profiles);
                System.out.println("This part did work!");
            }
        });
    }

    public void listenActions() {
        chatUsersRecyclerViewModel.observeAction(this, new Observer<Action<Profile, BasicAction>>() {
            @Override
            public void onChanged(@Nullable Action<Profile, BasicAction> messageBasicActionAction) {
                if (messageBasicActionAction != null) {
                    if (messageBasicActionAction.getActionType() == BasicAction.ON_BUTTON_CLICK) {
                        ((ChatActivity) getActivity()).mChatActivityViewModel.showUsers.set(false);
                        ((ChatActivity) getActivity()).mChatActivityViewModel.mSearching.set(false);
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.chat_users_fragment;
    }

    private void adaptRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.chat_users_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        chatUsersRecyclerAdapter = new ChatUsersRecyclerAdapter(chatUsersRecyclerViewModel, this);
        recyclerView.setAdapter(chatUsersRecyclerAdapter);
    }

    public ChatUsersRecyclerViewModel getChatUsersRecyclerViewModel() {
        return chatUsersRecyclerViewModel;
    }

}
