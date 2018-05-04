package com.cutloose.cutloose.ui.common;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState ) {
        super.onCreate( savedInstanceState, persistentState );

        setContentView( getViewId() );
    }

    protected abstract int getViewId();
}
