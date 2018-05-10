package com.cutloose.cutloose.ui.common;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cutloose.cutloose.model.BaseModel;

public abstract class BaseViewModel<Model extends BaseModel> extends ViewModel {

    protected MutableLiveData<Action<Model>> mAction = new MutableLiveData<>();

    public MutableLiveData<Action<Model>> getAction() {
        return this.mAction;
    }

    public void emitAction( Model model, ActionType actionType ) {
        this.mAction.postValue( new Action<>( model, actionType ) );
    }
}
