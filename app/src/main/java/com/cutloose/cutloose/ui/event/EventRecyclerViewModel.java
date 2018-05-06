package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.repository.EventRepository;
import com.cutloose.cutloose.ui.common.BaseViewModel;

import java.util.ArrayList;

class EventRecyclerViewModel extends BaseViewModel {

    private final EventRepository mEventRepository = new EventRepository();
    private final MutableLiveData<ArrayList<Event>> mEventList = new MutableLiveData<>();

    public void fetchEvents() {

        if( mEventList.getValue() == null ) {
            mEventList.setValue( mEventRepository.getAll() );
        }
    }

    public MutableLiveData<ArrayList<Event>> getEventList() {
        return mEventList;
    }
}
