package com.cutloose.cutloose.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.cutloose.cutloose.R;
import com.cutloose.cutloose.databinding.LoginActivityBinding;
import com.cutloose.cutloose.ui.common.BaseActivity;
import com.cutloose.cutloose.ui.event.EventActivity;
import com.cutloose.cutloose.utils.FirebaseActions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity {
    LoginActivityBinding loginActivityBinding;
    LoginViewModel loginViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.login_button);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginActivityBinding = (LoginActivityBinding) mViewDataBinding;
        loginActivityBinding.setViewModel(loginViewModel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    public void logIn(View v) {
        if (loginViewModel.email.get() == null || loginViewModel.email.get().equals("")
                || loginViewModel.password.get() == null || loginViewModel.password.get().equals("")) {
            loginViewModel.errorExists.set(true);
            loginViewModel.errorMessage.set("Fill all the fields!");
            return;
        }
        loginViewModel.login().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                handleSuccessfulTask(task);
                if (task.isSuccessful())
                    FirebaseActions.getInstance().loadProfile();
            }
        });
    }

    public void register(View v) {
        if (!loginViewModel.registerMode.get()) {
            loginViewModel.registerMode.set(true);
            return;
        }
        if (loginViewModel.email.get() == null || loginViewModel.email.get().equals("")
                || loginViewModel.password.get() == null || loginViewModel.password.get().equals("")
                || loginViewModel.profilePicture.get() == null || loginViewModel.profilePicture.get().equals("")
                || loginViewModel.username.get() == null || loginViewModel.username.get().equals("")) {
            loginViewModel.errorExists.set(true);
            loginViewModel.errorMessage.set("Fill all the fields!");
            return;
        }
        loginViewModel.register().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                handleSuccessfulTask(task);
                if (task.isSuccessful())
                    FirebaseActions.getInstance().saveProfile(loginViewModel.username.get(), loginViewModel.profilePicture.get());
            }
        });
    }

    private void handleSuccessfulTask(Task<AuthResult> task) {
        loginViewModel.loading.set(false);
        if (task.isSuccessful()) {
            startEventActivity();
        } else {
            loginViewModel.errorMessage.set(task.getException().getMessage());
            loginViewModel.errorExists.set(true);
        }
    }

    private void startEventActivity() {
        Intent intent = new Intent(this, EventActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
