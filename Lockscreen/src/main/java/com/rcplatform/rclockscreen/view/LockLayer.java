package com.rcplatform.rclockscreen.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by zuo on 2016/4/5.
 */
public class LockLayer {

    private Context context = null;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams lp;
    private boolean isLock = false;//是否锁住

    public LockLayer(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        if(context == null){
            throw new RuntimeException("LockLayer的context为空");
        }
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        lp.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        lp.flags |= WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
        lp.flags |= WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        lp.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            lp.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            lp.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.x = 0;
        lp.y = 0;
    }

    public synchronized void lock(View view){
        if(mWindowManager != null && view != null){
            mWindowManager.addView(view,lp);
        }
    }

    public synchronized void unLock(View view){
        if(mWindowManager != null && view != null){
            mWindowManager.removeViewImmediate(view);
        }
    }

    public synchronized void updateLock(View view){
        if(mWindowManager != null){
            mWindowManager.updateViewLayout(view,lp);
        }
    }
}
