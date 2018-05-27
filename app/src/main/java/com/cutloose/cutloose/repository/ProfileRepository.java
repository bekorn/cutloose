package com.cutloose.cutloose.repository;

import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.utils.FirebaseActions;


public class ProfileRepository {
    private static final ProfileRepository ourInstance = new ProfileRepository();

    public static ProfileRepository getInstance() {
        return ourInstance;
    }

    public void setUserProfile(MutableLiveData<Profile> mut) {
        mut.setValue(FirebaseActions.getInstance().getProfile());
    }
}
