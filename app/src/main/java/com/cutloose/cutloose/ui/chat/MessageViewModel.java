package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.ViewModel;

import com.cutloose.cutloose.model.Message;

public class MessageViewModel extends ViewModel {

    private Message message;
    private Boolean owned;

    public MessageViewModel( Message message ) {
        this.message = message;
    }

    public boolean checkOwnership() {
        if(owned != null) return owned;

        owned = message.getUserId().equals("0");
        //TODO: Check it with real fireid

        return owned;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
