package com.cutloose.cutloose.ui.profile.my_chats;


import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class MyChatsRecyclerViewModel extends BaseRecyclerViewModel<Chat, BasicAction> {

    @Override
    public void fetchData() {
        ChatRepository chatRepository = ChatRepository.getInstance();
        chatRepository.getChats( mData );
    }
}
