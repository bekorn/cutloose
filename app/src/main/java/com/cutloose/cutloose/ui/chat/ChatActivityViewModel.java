package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.BaseViewModel;
import com.cutloose.cutloose.utils.FirebaseActions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChatActivityViewModel extends BaseViewModel {
    public ObservableBoolean mSearching = new ObservableBoolean();
    public ObservableBoolean showUsers = new ObservableBoolean();
    private MutableLiveData<ArrayList<Profile>> chatUsers = new MutableLiveData<>();
    private final String TAG = "ChatActivityViewModel";
    private Chat currentChat = null;
    private boolean creatingState = false;

    public void checkExistingLobbies(String eventId) {
        FirebaseActions.getInstance().findLobby(eventId).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().isEmpty()) {
                    Log.d(TAG, "Couldn't find any lobbies, creating a new.");
                    createNewLobby(eventId);
                } else {
                    Log.d(TAG, "A lobby was found! Joining.");
                    for(DocumentSnapshot ds : task.getResult().getDocuments()) {
                        currentChat = ds.toObject(Chat.class);
                        currentChat.setId(ds.getId());
                    }
                    FirebaseActions.getInstance().joinLobby(eventId, currentChat.getId());
                    FirebaseActions.getInstance().saveChat(currentChat);
                    informChatFound();
                    listenJoiningUsers(eventId, currentChat.getId());
                }
            }
        });
    }

    public void createNewLobby(String eventId) {
        creatingState = true;
        Chat chat = new Chat();
        chat.setCreatedAt(System.currentTimeMillis());
        chat.setEventId(eventId);
        String chatId = FirebaseActions.getInstance().createLobby(eventId, chat);
        FirebaseActions.getInstance().joinLobby(eventId, chatId);
        chat.setId(chatId);
        currentChat = chat;
        FirebaseActions.getInstance().saveChat(chat);
        Log.d(TAG, "A lobby with id " + chatId + " was created, waiting for another user.");
        listenJoiningUsers(eventId, chatId);
    }

    public void listenJoiningUsers(String eventId, String chatId) {
        FirebaseActions.getInstance().listenJoinedUsers(eventId, chatId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                System.out.println("Size LUL" + queryDocumentSnapshots.size());
                 if(creatingState && queryDocumentSnapshots.size() > 1) {
                     informChatFound();
                     creatingState = false;
                 }
                 ArrayList<Profile> usrs = chatUsers.getValue() == null ? new ArrayList<>() : chatUsers.getValue();
                 for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                     usrs.add((doc.getDocument().toObject(Profile.class)));
                 }
                 chatUsers.setValue(usrs);
            }
        });
    }

    public void informChatFound() {
        Action<Chat, LobbyAction> action = new Action<>(currentChat, LobbyAction.ON_CHAT_FOUND);
        setAction(action);
        setAction(null);
        mSearching.set(false);
    }

    public Chat getCurrentChat() {
        return currentChat;
    }

    public MutableLiveData<ArrayList<Profile>> getChatUsers() {
        return chatUsers;
    }

}
