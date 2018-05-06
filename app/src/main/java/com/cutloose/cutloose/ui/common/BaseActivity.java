package com.cutloose.cutloose.ui.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity {
    protected ViewDataBinding binding;
    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        binding = DataBindingUtil.setContentView( this, getViewId() );
    }

    protected abstract int getViewId();
}
