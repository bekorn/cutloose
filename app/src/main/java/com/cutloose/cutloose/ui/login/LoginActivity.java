package com.cutloose.cutloose.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.ui.chat.ChatActivity;
import com.cutloose.cutloose.ui.common.BaseActivity;

public class LoginActivity extends BaseActivity {


    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        TextView signUp = findViewById(R.id.login_activity_text_sign_up);
        signUp.setMovementMethod(LinkMovementMethod.getInstance());
        TextView forgotPassword = findViewById(R.id.login_activity_text_forgot_password);
        forgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
    }
    @Override
    protected int getViewId() {
        return R.layout.login_activity;
    }

    public void logIn(View view){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
