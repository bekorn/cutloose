package com.cutloose.cutloose.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.ui.chat.ChatActivity;

/**
 * Created by finge on 5/6/2018.
 */

public class MyChatsItemViewModel {
    private Chat chat;


    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public String showOwners(){
        int length = chat.getOwners().size() < 3 ? chat.getOwners().size() : 3;
        String ownersString = "Chat with: ";
        for(int i = 0 ; i < length; i++){
            ownersString = ownersString + chat.getOwners().get(i).getName() + ", ";
        }
        return ownersString;
    }

    public void onClickItem(View v) {
        //TODO: This process should be done in Activity instead of View Model
        Context context = v.getContext();

        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("isProfile", true);
        intent.putExtra("owners",showOwners());
        context.startActivity(intent);
    }
}
