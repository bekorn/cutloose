package com.cutloose.cutloose.ui.chat;

import android.databinding.ObservableField;

import com.cutloose.cutloose.model.BaseModel;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;
import com.cutloose.cutloose.utils.FirebaseActions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatFragmentRecyclerViewModel extends BaseRecyclerViewModel <Message, BasicAction> {

    public ObservableField<String> messageInputContent = new ObservableField<>();
    private String eventId, chatId;

    @Override
    public void fetchData() {
    }

    public void fetchData(String eventId, String chatId) {
        ChatRepository chatRepository = ChatRepository.getInstance();
        chatRepository.getChatMessages( chatId, eventId, mData );
        this.eventId = eventId;
        this.chatId = chatId;
    }

    public void onMessageSendButtonClicked() {
        String messageText = messageInputContent.get();

        if(messageText == null ||messageText.equals("")) return;

        Message message = new Message();
        message.setUserName(FirebaseActions.getInstance().getProfile().getName());
        message.setContent( messageText );
        message.setCreatedAt( System.currentTimeMillis());
        message.setUserId(FirebaseAuth.getInstance().getUid());

        FirebaseActions.getInstance().sendMessage(eventId, chatId, message);

        messageInputContent.set( "" );
    }

    public void onShowUsersClicked() {
        Action<Message, BasicAction> newAction = new Action<>(null, BasicAction.ON_BUTTON_CLICK);
        setAction(newAction);
        setAction(null);
    }

    public ObservableField<String> getMessageInputContent() {
        return messageInputContent;
    }

    public void setMessageInputContent( ObservableField<String> messageInputContent ) {
        this.messageInputContent = messageInputContent;
    }
}
