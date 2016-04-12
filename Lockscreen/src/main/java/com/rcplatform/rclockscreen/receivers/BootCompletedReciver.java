package com.rcplatform.rclockscreen.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rcplatform.rclockscreen.services.Service1;


/**
 * Created by zuo on 2016/4/1.
 */
public class BootCompletedReciver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, Service1.class));
    }
}
