package com.cutloose.cutloose.ui.chat;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.BaseViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<Message>> messages = new MutableLiveData<>();
    public ObservableField<String> messageInputContent = new ObservableField<>();

    public void fetchData() {
        ChatRepository chatRepository = ChatRepository.getInstance();
        //TODO: Request the messages by a real ID
        chatRepository.getChatMessages( null, messages );
    }

    public void onMessageSendButtonClicked() {
        String messageText = messageInputContent.get();

        Message message = new Message();
        message.setContent( messageText );
        message.setDate( Calendar.getInstance().getTime() );
        message.setUserId( "0" ); //TODO: This id must be real user ID.

        //TODO: Send this new message to the firebase database.
        ArrayList<Message> messageArrayList = messages.getValue();
        messageArrayList.add( message );
        messages.setValue( messageArrayList );

        messageInputContent.set( "" );
    }

    public MutableLiveData<ArrayList<Message>> getMessages() {
        return messages;
    }

    public void setMessages( MutableLiveData<ArrayList<Message>> messages ) {
        this.messages = messages;
    }

    public ObservableField<String> getMessageInputContent() {
        return messageInputContent;
    }

    public void setMessageInputContent( ObservableField<String> messageInputContent ) {
        this.messageInputContent = messageInputContent;
    }
}
