package com.cutloose.cutloose.ui.event;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.ui.chat.ChatActivity;
import com.cutloose.cutloose.ui.common.BaseActivity;
import com.cutloose.cutloose.ui.profile.ProfileActivity;

public class EventActivity extends BaseActivity {

    static private final String TAG = "EventActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.event_activity;
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setTitle( R.string.event_title );

        EventRecyclerViewModel eventRecyclerViewModel = ViewModelProviders.of( this ).get( EventRecyclerViewModel.class );

        EventRecyclerViewAdapter eventRecyclerViewAdapter = new EventRecyclerViewAdapter( eventRecyclerViewModel, this );

        eventRecyclerViewAdapter.mOnItemClickEvent.observe( this, new Observer<Event>() {
            @Override
            public void onChanged( @Nullable Event event ) {
                Intent intent = new Intent( EventActivity.this, ChatActivity.class );
                startActivity( intent );
            }
        } );

        RecyclerView recyclerView = findViewById( R.id.event_recycler_view );

        recyclerView.setLayoutManager( new GridLayoutManager( this, 3 ) );
        recyclerView.setAdapter( eventRecyclerViewAdapter );

        eventRecyclerViewModel.fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.titlebar_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch( item.getItemId() ) {
            case R.id.menu_profile:
                startProfileActivity();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }

    }

    private void startProfileActivity() {
        Intent intent = new Intent( this, ProfileActivity.class );
        startActivity( intent );
    }
}
