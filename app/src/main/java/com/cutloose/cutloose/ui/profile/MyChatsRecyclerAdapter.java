package com.cutloose.cutloose.ui.profile;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.MyChatsItemBinding;
import com.cutloose.cutloose.model.Chat;

import java.util.ArrayList;

/**
 * Created by finge on 5/6/2018.
 */

public class MyChatsRecyclerAdapter extends RecyclerView.Adapter<MyChatsRecyclerAdapter.MyChatViewHolder> {

    private ArrayList<Chat> chats;



    public static class MyChatViewHolder extends RecyclerView.ViewHolder {
        private MyChatsItemBinding myChatsItemBinding;

        public MyChatViewHolder(MyChatsItemBinding myChatsItemBinding) {
            super(myChatsItemBinding.getRoot());
            this.myChatsItemBinding = myChatsItemBinding;
        }

        public MyChatsItemBinding getMyChatsItemBinding() {
            return myChatsItemBinding;
        }
    }

    public MyChatsRecyclerAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }
    @NonNull
    @Override
    public MyChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyChatsItemBinding myChatsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_chats_item, parent, false);
        return new MyChatsRecyclerAdapter.MyChatViewHolder(myChatsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatViewHolder holder, int position) {
        MyChatsItemViewModel myChatsItemViewModel = new MyChatsItemViewModel();
        myChatsItemViewModel.setChat(chats.get(position));
        holder.getMyChatsItemBinding().setViewModel(myChatsItemViewModel);

    }

    public void updateItems(final ArrayList<Chat> newChats) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return chats.size();
            }

            @Override
            public int getNewListSize() {
                return newChats.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return chats.get(oldItemPosition).equals(newChats.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return chats.get(oldItemPosition).getId().equals(newChats.get(newItemPosition).getId());
            }
        });

        chats.clear();

        chats.addAll(newChats);

        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
