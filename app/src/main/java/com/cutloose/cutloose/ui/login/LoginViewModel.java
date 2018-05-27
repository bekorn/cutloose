package com.cutloose.cutloose.ui.login;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.cutloose.cutloose.ui.common.BaseViewModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends BaseViewModel {
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> profilePicture = new ObservableField<>();
    public ObservableBoolean errorExists = new ObservableBoolean();
    public ObservableBoolean registerMode = new ObservableBoolean();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableBoolean loading = new ObservableBoolean();

    private void commonOperations() {
        errorMessage.set("");
        errorExists.set(false);
        loading.set(true);
    }

    public Task<AuthResult> login() {
        commonOperations();
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email.get(), password.get());
    }
    public Task<AuthResult> register() {
        commonOperations();
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.get(), password.get());
    }

}
