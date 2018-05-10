package com.cutloose.cutloose.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.ui.common.BaseActivity;
import com.cutloose.cutloose.ui.event.EventActivity;

public class LoginActivity extends BaseActivity {


    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        logIn();
        setTitle(R.string.login_button);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    public void logIn(){
        final Context context = this;

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventActivity.class);
                startActivity(intent);
            }
        });
    }
}
