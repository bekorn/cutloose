package com.cutloose.cutloose.ui.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {
    protected ViewDataBinding binding;

    static private final String TAG = "BaseActivity";

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        binding = DataBindingUtil.setContentView( this, getViewId() );
        setContentView( getViewId() );

        Log.d( TAG, "Created -> " + getResources().getResourceName( getViewId() ) );
    }

    protected abstract int getViewId();
}
