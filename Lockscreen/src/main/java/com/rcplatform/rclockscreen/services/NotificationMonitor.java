package com.rcplatform.rclockscreen.services;

import android.annotation.TargetApi;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by zuo on 2016/4/6.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationMonitor extends NotificationListenerService{
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
