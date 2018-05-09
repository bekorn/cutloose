package com.cutloose.cutloose.ui.common.RecyclerView;

import android.arch.lifecycle.MutableLiveData;

import com.cutloose.cutloose.ui.common.BaseViewModel;

import java.util.ArrayList;

public abstract class BaseRecyclerViewModel <Model> extends BaseViewModel {

    protected MutableLiveData<ArrayList<Model>> mData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Model>> getLiveData() {
        return mData;
    }

    public abstract void fetchData();
}
