package com.rcplatform.rclockscreen.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcplatform.rclockscreen.R;

/**
 * Created by zuo on 2016/4/13.
 */
public class NotifycationAdapter extends BaseAdapter {

    private final StatusBarNotification[] notifications;

    public NotifycationAdapter(StatusBarNotification[] notifications) {
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.length;
    }

    @Override
    public Object getItem(int position) {
        return notifications[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(convertView.getContext(), R.layout.item_notification, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        StatusBarNotification notification = notifications[position];
        Bundle extras = notification.getNotification().extras;
        String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
        Bitmap notificationLargeIcon = ((Bitmap)extras.getParcelable(Notification.EXTRA_LARGE_ICON));
        Bitmap notificationSmallIcon = ((Bitmap)extras.getParcelable(Notification.EXTRA_SMALL_ICON));
        CharSequence notificationText = extras.getCharSequence(Notification.EXTRA_TEXT);
        CharSequence notificationSubText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

        holder.item_notify_icon.setImageBitmap(notificationSmallIcon);
        holder.item_notify_title.setText(notificationText);
        holder.item_notify_content.setText(notificationSubText);

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView item_notify_icon;
        public TextView item_notify_title;
        public TextView item_notify_content;
        public TextView item_notify_time;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_notify_icon = (ImageView) rootView.findViewById(R.id.item_notify_icon);
            this.item_notify_title = (TextView) rootView.findViewById(R.id.item_notify_title);
            this.item_notify_content = (TextView) rootView.findViewById(R.id.item_notify_content);
            this.item_notify_time = (TextView) rootView.findViewById(R.id.item_notify_time);
        }

    }
}
