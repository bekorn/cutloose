package com.cutloose.cutloose.model;

import android.databinding.BaseObservable;

public abstract class BaseModel extends BaseObservable {

    public String getContent() {
        return toString();
    }
}
