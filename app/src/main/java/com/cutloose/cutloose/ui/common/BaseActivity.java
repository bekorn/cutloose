package com.cutloose.cutloose.ui.common;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {

    static private final String TAG = "BaseActivity";

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        setContentView( getViewId() );

        Log.d( TAG, "Created -> " + getResources().getResourceName( getViewId() ) );
    }

    protected abstract int getViewId();
}
