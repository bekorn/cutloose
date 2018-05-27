package com.cutloose.cutloose.ui.profile;

import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.repository.ProfileRepository;
import com.cutloose.cutloose.ui.common.BaseViewModel;

public class ProfileViewModel extends BaseViewModel {
    private MutableLiveData<Profile> profile = new MutableLiveData<>();

    public void fetchData() {
        ProfileRepository.getInstance().setUserProfile(profile);
    }

    public Profile getProfile() {
        return profile.getValue();
    }

}
