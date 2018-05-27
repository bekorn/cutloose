package com.cutloose.cutloose.utils;

import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.cutloose.cutloose.model.Chat;
import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.model.Message;
import com.cutloose.cutloose.model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirebaseActions {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Profile profile;
    private static FirebaseActions instance;

    private FirebaseActions() {

    }

    public static FirebaseActions getInstance() {
        if(instance == null) instance = new FirebaseActions();
        return instance;
    }

    public Task getEvents() {
        return firestore
                .collection("events")
                .get();
    }

    public Task findLobby(String eventId) {
        long tenMinutesAgo = System.currentTimeMillis() - 1000 * 60 * 10;

        return firestore
                .collection("events")
                .document(eventId)
                .collection("lobbies")
                .whereGreaterThan("createdAt", tenMinutesAgo)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(1)
                .get();
    }

    public String createLobby(String eventId, Chat c) {
        DocumentReference dr = firestore
                .collection("events")
                .document(eventId)
                .collection("lobbies")
                .document();

        dr.set(c);

        return dr.getId();
    }

    public Task joinLobby(String eventId, String chatId) {
        return firestore
                .collection("events")
                .document(eventId)
                .collection("lobbies")
                .document(chatId)
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .set(profile);
    }

    public CollectionReference listenJoinedUsers(String eventId, String chatId) {
        return firestore
                .collection("events")
                .document(eventId)
                .collection("lobbies")
                .document(chatId)
                .collection("users");
    }

    public Task saveChat(Chat chat) {
        return firestore
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("chats")
                .document(chat.getId())
                .set(chat);
    }

    public Task getChats() {
        return firestore
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("chats")
                .get();
    }

    public Task getChatUsers(String eventId, String chatId) {
        return listenJoinedUsers(eventId, chatId).get();
    }

    public CollectionReference getMessages(String eventId, String chatId) {
        return firestore
                .collection("events")
                .document(eventId)
                .collection("lobbies")
                .document(chatId)
                .collection("messages");
    }

    public Task sendMessage(String eventId, String chatId, Message message) {
        return firestore
                .collection("events")
                .document(eventId)
                .collection("lobbies")
                .document(chatId)
                .collection("messages")
                .document()
                .set(message);
    }

    public void loadProfile() {
        firestore
            .collection("users")
            .document(FirebaseAuth.getInstance().getUid())
            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    profile = task.getResult().toObject(Profile.class);
                }
            }
        });
    }

    public void saveProfile(String name, String profilePictureURL) {
        Profile profile = new Profile();
        profile.setId(FirebaseAuth.getInstance().getUid());
        profile.setPhotoPath(profilePictureURL);
        profile.setName(name);
        firestore
            .collection("users")
            .document(FirebaseAuth.getInstance().getUid())
            .set(profile)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            loadProfile();
                        }
                    }
                });
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
