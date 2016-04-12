package com.rcplatform.rclockscreen.application;

import android.content.Context;

import com.marswin89.marsdaemon.DaemonApplication;
import com.marswin89.marsdaemon.DaemonConfigurations;
import com.rcplatform.rclockscreen.receivers.Receiver1;
import com.rcplatform.rclockscreen.receivers.Receiver2;
import com.rcplatform.rclockscreen.services.Service1;
import com.rcplatform.rclockscreen.services.Service2;

public class MyApplication1 extends DaemonApplication {


    @Override
    public void attachBaseContextByDaemon(Context base) {
        super.attachBaseContextByDaemon(base);
    }


    @Override
    protected DaemonConfigurations getDaemonConfigurations() {
        DaemonConfigurations.DaemonConfiguration configuration1 = new DaemonConfigurations.DaemonConfiguration(
                "com.rcplatform.rclockscreen:process1",
                Service1.class.getCanonicalName(),
                Receiver1.class.getCanonicalName());

        DaemonConfigurations.DaemonConfiguration configuration2 = new DaemonConfigurations.DaemonConfiguration(
                "com.rcplatform.rclockscreen:process2",
                Service2.class.getCanonicalName(),
                Receiver2.class.getCanonicalName());

        DaemonConfigurations.DaemonListener listener = new MyDaemonListener();
        //return new DaemonConfigurations(configuration1, configuration2);//listener can be null
        return new DaemonConfigurations(configuration1, configuration2, listener);
    }


    class MyDaemonListener implements DaemonConfigurations.DaemonListener{
        @Override
        public void onPersistentStart(Context context) {
        }

        @Override
        public void onDaemonAssistantStart(Context context) {
        }

        @Override
        public void onWatchDaemonDaed() {
        }
    }
}
