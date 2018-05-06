package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.EventItemBinding;
import com.cutloose.cutloose.model.Event;

import java.util.ArrayList;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final MutableLiveData<ArrayList<Event>> mEventList = new MutableLiveData<>();

    public EventRecyclerViewAdapter( EventRecyclerViewModel eventRecyclerViewModel, LifecycleOwner lifecycleOwner ) {
        super();

        mEventList.setValue( new ArrayList<Event>() );

        eventRecyclerViewModel.getEventList().observe( lifecycleOwner, new Observer<ArrayList<Event>>() {
            @Override
            public void onChanged( @Nullable ArrayList<Event> events ) {
                setEventList( events );
            }
        } );
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private EventItemBinding mBinding;

        public ViewHolder( View instructorView, EventItemBinding binding ) {
            super( instructorView );

            mBinding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        EventItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from( parent.getContext() ),
                R.layout.event_item,
                parent,
                false
        );

        return new ViewHolder( binding.getRoot(), binding );
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
        holder.mBinding.setViewModel( new EventItemViewModel( mEventList.getValue().get( position ) ) );
    }

    @Override
    public int getItemCount() {
        return mEventList.getValue().size();
    }

    public void setEventList( ArrayList<Event> eventList ) {
        mEventList.setValue( eventList );
        notifyDataSetChanged();
    }
}
