package com.cutloose.cutloose.ui.profile;


import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.BaseViewModel;

import java.util.ArrayList;

public class MyChatsFragmentViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<Chat>> chats = new MutableLiveData<>();

    public void fetchData() {
        ChatRepository chatRepository = ChatRepository.getInstance();
        //TODO: Request the messages by a real ID
        chatRepository.getChats(null, chats);
    }

    public MutableLiveData<ArrayList<Chat>> getChats() {
        return chats;
    }
}
