package com.example.fanxh.zodiacchart.Util;

import android.app.Activity;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.View;

/**
 * Created by fanxh on 2017/11/23.
 */

public class SetStatusBar {
    private View statusBarView;
    private Activity activity;
    private int id;

    public SetStatusBar(final Activity activity, int id) {
        this.activity = activity;
        this.id = id;
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                if (isStatusBar()) {
                    initStatusBar();
                    activity.getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            initStatusBar();
                        }
                    });
                }
                return false;
            }
        });
    }

    private void initStatusBar() {
        if (statusBarView == null) {
            int identifier = activity.getResources().getIdentifier("statusBarBackground", "id", "android");
            statusBarView = activity.getWindow().findViewById(identifier);
        }
        if (statusBarView != null) {
            statusBarView.setBackgroundResource(id);
        }
    }

    protected boolean isStatusBar() {
        return true;
    }
}
