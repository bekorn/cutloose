package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.cutloose.cutloose.BR;
import com.cutloose.cutloose.R;
import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewAdapter;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

public class EventRecyclerViewAdapter extends BaseRecyclerViewAdapter<Event, BasicAction> {

    public EventRecyclerViewAdapter( BaseRecyclerViewModel<Event, BasicAction> baseRecyclerViewModel, LifecycleOwner lifecycleOwner ) {
        super( baseRecyclerViewModel, lifecycleOwner );
    }

    @Override
    protected void setViewHolderBindings( ViewDataBinding binding, Event event ) {
        binding.setVariable( BR.viewModel, new EventItemViewModel( event ) );

        binding.getRoot().setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                mViewModel.setAction( new Action<>( event, BasicAction.RECYCLER_ITEM_CLICK ) );
            }
        } );
    }

    @Override
    protected int getItemViewId() {
        return R.layout.event_item;
    }
}
