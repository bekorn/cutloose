package com.cutloose.cutloose.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.utils.FirebaseActions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventRepository {

    public void getEvents(MutableLiveData<ArrayList<Event>> events) {
        final ArrayList<Event> eventsList = new ArrayList<>();
        FirebaseActions.getInstance().getEvents().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot qs = task.getResult();
                    for (DocumentSnapshot ds : qs.getDocuments()) {
                        Event event = ds.toObject(Event.class);
                        event.setEventId(ds.getId());
                        eventsList.add(event);
                    }
                    events.setValue(eventsList);
                }
            }
        });
    }
}