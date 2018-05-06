package com.cutloose.cutloose.ui.profile;


import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

import java.util.ArrayList;

public class MyChatsFragmentViewModel extends BaseRecyclerViewModel<Chat> {

    private MutableLiveData<ArrayList<Chat>> chats = new MutableLiveData<>();

    @Override
    public MutableLiveData<ArrayList<Chat>> getLiveData() {
        return chats;
    }

    @Override
    public void fetchData() {
        ChatRepository chatRepository = ChatRepository.getInstance();
        //TODO: Request the messages by a real ID
        chatRepository.getChats(null, chats);
    }
}
