package com.cutloose.cutloose.ui.common;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.cutloose.cutloose.model.BaseModel;
import com.cutloose.cutloose.ui.common.Action.Action;

public abstract class BaseViewModel<Model extends BaseModel, ActionType> extends ViewModel {

    private final MutableLiveData<Action<Model, ActionType>> mCurrentAction = new MutableLiveData<>();

    public void setAction(Action<Model, ActionType> action) {
        mCurrentAction.setValue(action);
    }

    public void observeAction(final LifecycleOwner lifecycleOwner, final Observer<Action<Model, ActionType>> observer) {
        mCurrentAction.observe(lifecycleOwner, observer);
    }
}
