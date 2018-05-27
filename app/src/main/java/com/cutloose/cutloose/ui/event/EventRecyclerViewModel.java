package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.repository.EventRepository;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class EventRecyclerViewModel extends BaseRecyclerViewModel<Event, BasicAction> {

    private final EventRepository mEventRepository = new EventRepository();
    public ObservableBoolean loading = new ObservableBoolean();

    @Override
    public void fetchData() {
        loading.set(true);
        System.out.println(loading.get());
        mEventRepository.getEvents(mData);
    }
}
