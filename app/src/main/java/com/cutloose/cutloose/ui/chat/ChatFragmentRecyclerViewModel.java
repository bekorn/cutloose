package com.cutloose.cutloose.ui.chat;

import android.databinding.ObservableField;

import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatFragmentRecyclerViewModel extends BaseRecyclerViewModel <Message> {

    public ObservableField<String> messageInputContent = new ObservableField<>();

    @Override
    public void fetchData() {
        ChatRepository chatRepository = ChatRepository.getInstance();
        //TODO: Request the messages by a real ID
        chatRepository.getChatMessages( null, mData );
    }

    public void onMessageSendButtonClicked() {
        String messageText = messageInputContent.get();

        Message message = new Message();
        message.setContent( messageText );
        message.setDate( Calendar.getInstance().getTime() );
        message.setUserId( "0" ); //TODO: This id must be real user ID.

        //TODO: Send this new message to the firebase database.
        ArrayList<Message> messageArrayList = mData.getValue();
        messageArrayList.add( message );
        mData.setValue( messageArrayList );

        messageInputContent.set( "" );
    }

    public ObservableField<String> getMessageInputContent() {
        return messageInputContent;
    }

    public void setMessageInputContent( ObservableField<String> messageInputContent ) {
        this.messageInputContent = messageInputContent;
    }
}
