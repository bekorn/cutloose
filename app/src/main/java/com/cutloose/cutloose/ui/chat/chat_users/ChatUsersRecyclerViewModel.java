package com.cutloose.cutloose.ui.chat.chat_users;

import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.model.Profile;
import com.cutloose.cutloose.ui.common.Action.Action;
import com.cutloose.cutloose.ui.common.Action.BasicAction;
import com.cutloose.cutloose.ui.common.BaseViewModel;
import com.cutloose.cutloose.ui.common.RecyclerView.BaseRecyclerViewModel;

import java.util.ArrayList;

public class ChatUsersRecyclerViewModel extends BaseRecyclerViewModel<Profile, BasicAction> {
    private MutableLiveData<ArrayList<Profile>> users = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Profile>> getUsers() {
        return users;
    }

    public void setUsers(MutableLiveData<ArrayList<Profile>> users) {
        this.users = users;
    }

    @Override
    public void fetchData() {

    }

    public void onCloseUsersClicked() {
        Action<Profile, BasicAction> newAction = new Action<>(null, BasicAction.ON_BUTTON_CLICK);
        setAction(newAction);
        setAction(null);
    }
}
