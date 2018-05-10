package com.cutloose.cutloose.ui.common;

import com.cutloose.cutloose.model.BaseModel;

public class Action<Model extends BaseModel> {

    private Model mModel;
    private ActionType mActionType;

    public Action( Model model, ActionType actionType ) {
        mModel = model;
        mActionType = actionType;
    }

    public Model getModel() {
        return mModel;
    }

    public ActionType getActionType() {
        return mActionType;
    }
}
