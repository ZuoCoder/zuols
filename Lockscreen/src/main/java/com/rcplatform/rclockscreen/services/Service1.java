package com.rcplatform.rclockscreen.services;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.ui.BaseLockActivity;
import com.rcplatform.rclockscreen.ui.NumLockActivity;
import com.rcplatform.rclockscreen.ui.PicLockActivity;
import com.rcplatform.rclockscreen.utils.Constant;

public class Service1 extends Service {

    private UnLockScreenReciver reciver;
    private KeyguardManager.KeyguardLock unLock;

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO do some thing what you want..
        Log.i("zuo", "service创建了");
        KeyguardManager km = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        unLock = km.newKeyguardLock("");

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        reciver = new UnLockScreenReciver();
        registerReceiver(reciver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("zuo", "service1关闭了");
        unLock.reenableKeyguard();
        unregisterReceiver(reciver);
        if (!SharedPreferenceUtils.getBoolean(getApplicationContext(), "UserClose", false)) {
            startService(new Intent(Service1.this, Service1.class));
        }
    }

    class UnLockScreenReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                unLock.disableKeyguard();
            }else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                int typle = SharedPreferenceUtils.getInt(context, Constant.LOCKSTYLE_TYPLES, 1);
                if(typle == 1){
                    startLock(context,BaseLockActivity.class);
                }else if(typle == 2){
                    startLock(context,BaseLockActivity.class);
                }else if(typle == 3){
                    startLock(context,PicLockActivity.class);
                }else if(typle == 4){
                    startLock(context,NumLockActivity.class);
                }
            }
        }

        private void startLock(Context context,Class clazz) {
            Intent slideIntent = new Intent(context,clazz);
            slideIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(slideIntent);
        }
    }
}
