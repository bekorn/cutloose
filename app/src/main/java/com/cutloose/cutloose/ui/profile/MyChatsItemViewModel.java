package com.cutloose.cutloose.ui.profile;

import com.cutloose.cutloose.model.Chat;

/**
 * Created by finge on 5/6/2018.
 */

public class MyChatsItemViewModel {
    private Chat mChat;

    public MyChatsItemViewModel( Chat chat ) {
        mChat = chat;
    }

    public void setChat( Chat chat ) {
        mChat = chat;
    }

    public Chat getChat() {
        return mChat;
    }
}
