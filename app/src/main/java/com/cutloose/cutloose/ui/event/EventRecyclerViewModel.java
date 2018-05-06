package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.repository.EventRepository;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

import java.util.ArrayList;

public class EventRecyclerViewModel extends BaseRecyclerViewModel <Event> {

    private final EventRepository mEventRepository = new EventRepository();
    private final MutableLiveData<ArrayList<Event>> mEventList = new MutableLiveData<>();

    @Override
    public MutableLiveData<ArrayList<Event>> getLiveData() {
        return mEventList;
    }

    @Override
    public void fetchData() {
        if( mEventList.getValue() == null ) {
            mEventList.setValue( mEventRepository.getAll() );
        }
    }
}
