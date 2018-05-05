package com.cutloose.cutloose.repository;


import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Message;

import java.util.ArrayList;

public class ChatRepository {
    private static final ChatRepository ourInstance = new ChatRepository();

    public static ChatRepository getInstance() {
        return ourInstance;
    }

    private ChatRepository() {
    }

    public void getChatMessages(String chatId, MutableLiveData<ArrayList<Message>> messages) {
        ArrayList<Message> messageList = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setUserId((i % 2) + "");
            message.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            messageList.add(message);
        }

        messages.setValue(messageList);
    }
}
