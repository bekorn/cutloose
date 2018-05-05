package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.ChatMessageItemBinding;
import com.cutloose.cutloose.model.Message;
import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MessageViewHolder> {

    private ArrayList<Message> messages;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private ChatMessageItemBinding chatMessageItemBinding;

        public MessageViewHolder(ChatMessageItemBinding chatMessageItemBinding) {
            super(chatMessageItemBinding.getRoot());
            this.chatMessageItemBinding = chatMessageItemBinding;
        }

        public ChatMessageItemBinding getChatMessageItemBinding() {
            return chatMessageItemBinding;
        }
    }

    public ChatRecyclerAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatMessageItemBinding chatMessageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.chat_message_item, parent, false);

        return new MessageViewHolder(chatMessageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageViewModel messageViewModel = new MessageViewModel();
        messageViewModel.setMessage(messages.get(position));
        holder.chatMessageItemBinding.setViewModel(messageViewModel);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void updateItems(final ArrayList<Message> newMessages) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return messages.size();
            }

            @Override
            public int getNewListSize() {
                return newMessages.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return messages.get(oldItemPosition).equals(newMessages.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return messages.get(oldItemPosition).getContent().equals(newMessages.get(newItemPosition).getContent());
            }
        });

        messages.clear();

        messages.addAll(newMessages);

        diffResult.dispatchUpdatesTo(this);
    }

}
