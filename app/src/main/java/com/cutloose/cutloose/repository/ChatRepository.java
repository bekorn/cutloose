package com.cutloose.cutloose.repository;


import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.model.Profile;

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

    public void getChats(String userId, MutableLiveData<ArrayList<Chat>> chats) {
        ArrayList<Chat> chatList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Event e = new Event();
            e.setName("Brunch");

            ArrayList<Profile> profiles = new ArrayList<>();

            for(int j = 0; j < 3; j++) {
                Profile p = new Profile();
                if(j == 0)  p.setName("Ali");
                else if(j == 1)  p.setName("Murat");
                else if(j == 2)  p.setName("Efe");
                profiles.add(p);
            }

            Chat chat = new Chat(i + "", e, profiles);
            chatList.add(chat);
        }

        chats.setValue(chatList);

    }
}
