package com.cutloose.cutloose.ui.event;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cutloose.cutloose.model.Event;
import com.cutloose.cutloose.ui.chat.ChatActivity;
import com.cutloose.cutloose.ui.common.BaseViewModel;

public class EventItemViewModel extends BaseViewModel {

    public Event mEvent;

    public EventItemViewModel( Event event ) {
        super();

        mEvent = event;
    }

    public void onClickItem(View v) {
        //TODO: This process should be done in Activity instead of View Model
        Context context = v.getContext();

        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }
}
