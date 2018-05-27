package com.cutloose.cutloose.repository;


import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.utils.FirebaseActions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChatRepository {
    private static final ChatRepository ourInstance = new ChatRepository();

    public static ChatRepository getInstance() {
        return ourInstance;
    }

    private ChatRepository() {
    }

    public void getChatMessages(String chatId, String eventId, MutableLiveData<ArrayList<Message>> messages) {
        ArrayList<Message> messageList = new ArrayList<>();

        FirebaseActions.getInstance().getMessages(eventId, chatId).orderBy("createdAt", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    messageList.add((doc.getDocument().toObject(Message.class)));

                }
                Collections.sort(messageList, new Comparator<Message>() {
                    @Override
                    public int compare(Message message, Message t1) {
                        return message.getCreatedAt().compareTo(t1.getCreatedAt());
                    }
                });
                messages.setValue(messageList);
//                recyclerView.smoothScrollToPosition(chatRecyclerAdapter.getItemCount());
            }
        });
    }

    public void getChats(MutableLiveData<ArrayList<Chat>> chats) {
        ArrayList<Chat> chatList = new ArrayList<>();

        FirebaseActions.getInstance().getChats().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(DocumentSnapshot qs : task.getResult().getDocuments()) {
                        final Chat chat = qs.toObject(Chat.class);
                        FirebaseActions.getInstance().getChatUsers(chat.getEventId(), chat.getId()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()) {
                                    ArrayList<Profile> users = new ArrayList<>();
                                    for(DocumentSnapshot qs : task.getResult().getDocuments()) {
                                        users.add(qs.toObject(Profile.class));
                                    }
                                    chat.setOwnersList(users);
                                }
                            }
                        });
                        chatList.add(chat);
                    }
                    chats.setValue(chatList);
                }
            }
        });
    }
}
