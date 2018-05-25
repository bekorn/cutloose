package com.cutloose.cutloose.ui.event;

import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.repository.EventRepository;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class EventRecyclerViewModel extends BaseRecyclerViewModel<Event, BasicAction> {

    private final EventRepository mEventRepository = new EventRepository();

    @Override
    public void fetchData() {
        if( mData.getValue() == null ) {
            mData.setValue( mEventRepository.getAll() );
        }
    }
}
