package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ViewDataBinding;

import com.cutloose.cutloose.BR;
import com.cutloose.cutloose.R;
import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewAdapter;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class EventRecyclerViewAdapter extends BaseRecyclerViewAdapter<Event> {

    public EventRecyclerViewAdapter( BaseRecyclerViewModel<Event> baseRecyclerViewModel, LifecycleOwner lifecycleOwner ) {
        super( baseRecyclerViewModel, lifecycleOwner );
    }

    @Override
    protected void setViewHolderBindings( ViewDataBinding binding, Event event ) {
        binding.setVariable( BR.viewModel, new EventItemViewModel( event ) );
    }

    @Override
    protected int getItemViewId() {
        return R.layout.event_item;
    }
}
