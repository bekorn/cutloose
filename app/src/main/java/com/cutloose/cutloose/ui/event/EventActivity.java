package com.cutloose.cutloose.ui.event;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.ui.common.BaseActivity;

public class EventActivity extends BaseActivity {

    static private final String TAG = "EventActivity";

    @Override
    protected int getViewId() {
        return R.layout.event_activity;
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState ) {
        super.onCreate( savedInstanceState, persistentState );

    }
}
