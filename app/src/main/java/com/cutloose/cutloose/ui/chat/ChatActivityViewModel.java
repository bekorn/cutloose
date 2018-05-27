package com.cutloose.cutloose.ui.chat;


import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.model.Event;
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

    public void checkExistingLobbies(Event event) {
        FirebaseActions.getInstance().findLobby(event.getEventId()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().isEmpty()) {
                    Log.d(TAG, "Couldn't find any lobbies, creating a new.");
                    createNewLobby(event);
                } else {
                    Log.d(TAG, "A lobby was found! Joining.");
                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                        currentChat = ds.toObject(Chat.class);
                        currentChat.setId(ds.getId());
                    }
                    FirebaseActions.getInstance().joinLobby(event.getEventId(), currentChat.getId());
                    FirebaseActions.getInstance().saveChat(currentChat);
                    informChatFound();
                    listenJoiningUsers(event.getEventId(), currentChat.getId());
                }
            }
        });
    }

    public void createNewLobby(Event event) {
        creatingState = true;
        Chat chat = new Chat();
        chat.setCreatedAt(System.currentTimeMillis());
        chat.setEventId(event.getEventId());
        chat.setEventType(event);
        String chatId = FirebaseActions.getInstance().createLobby(event.getEventId(), chat);
        FirebaseActions.getInstance().joinLobby(event.getEventId(), chatId);
        chat.setId(chatId);
        currentChat = chat;
        FirebaseActions.getInstance().saveChat(chat);
        Log.d(TAG, "A lobby with id " + chatId + " was created, waiting for another user.");
        listenJoiningUsers(event.getEventId(), chatId);
    }

    public void listenJoiningUsers(String eventId, String chatId) {
        FirebaseActions.getInstance().listenJoinedUsers(eventId, chatId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                System.out.println("Size LUL" + queryDocumentSnapshots.size());
                if (creatingState && queryDocumentSnapshots.size() > 1) {
                    informChatFound();
                    creatingState = false;
                }
                ArrayList<Profile> usrs = chatUsers.getValue() == null ? new ArrayList<>() : chatUsers.getValue();
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    Profile profile = (doc.getDocument().toObject(Profile.class));
                    boolean add = true;

                    for (int i = 0; i < usrs.size(); i++) {
                        if (profile.getId().equals(usrs.get(i).getId())) {
                            add = false;
                            break;
                        }
                    }

                    if (add) usrs.add(profile);
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
