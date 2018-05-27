package com.cutloose.cutloose.ui.event;


import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.ui.common.BaseViewModel;

public class EventItemViewModel extends BaseViewModel {

    public Event mEvent;

    public EventItemViewModel(Event event) {
        super();

        mEvent = event;
    }
}
