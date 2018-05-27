package com.cutloose.cutloose.ui.chat.chat_users;

import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.ui.common.BaseViewModel;

public class ChatUserItemViewModel extends BaseViewModel {

    private Profile profile;

    public ChatUserItemViewModel(Profile profile ) {
        super();
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
