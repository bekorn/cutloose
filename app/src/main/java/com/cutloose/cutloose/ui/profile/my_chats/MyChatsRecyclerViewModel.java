package com.cutloose.cutloose.ui.profile.my_chats;


import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.repository.ChatRepository;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class MyChatsRecyclerViewModel extends BaseRecyclerViewModel<Chat> {

    @Override
    public void fetchData() {
        ChatRepository chatRepository = ChatRepository.getInstance();
        //TODO: Request the messages by a real ID
        chatRepository.getChats( null, mData );
    }
}
