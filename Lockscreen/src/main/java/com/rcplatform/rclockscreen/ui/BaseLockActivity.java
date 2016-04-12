package com.rcplatform.rclockscreen.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.utils.MyDateUtils;
import com.rcplatform.rclockscreen.view.LockLayer;

import java.util.Date;

/**
 * Created by zuo on 2016/4/5.
 */
public class BaseLockActivity extends BaseActivity{

    protected static final int UNLOCK_SUCCESS = 0;//解锁成功
    protected static final int UPDATE_TIME = 1;//时间更新
    protected static final int UNLOCK_PWD_SUCCESS = 2;//密码解锁成功
    private LockLayer lockLayer;
    private boolean isTime = false;//是否更新时间
    private BatteryReciver batteryReciver;
    private View slideView = null;//滑动解锁
    private View pwdView = null;//密码解锁

    private TextView time;
    private TextView tv_date;
    private TextView batteryNum;
    private TextView iv_unlock;
    private int widthPixels = 0;
    private boolean isMoved = false;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private ImageView weather;
    private TextView tv_week;

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_TIME) {
                Date date = (Date) msg.obj;
                updateTimeComplete(date);
            }else if(msg.what == UNLOCK_SUCCESS){
                lockLayer.unLock(slideView);
                Log.i("zuo","pwdView==null?"+(pwdView==null));
                if(pwdView == null){
                    finish();
                }
            }else if(msg.what == UNLOCK_PWD_SUCCESS){
                lockLayer.unLock(pwdView);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryReciver = new BatteryReciver();
        registerReceiver(batteryReciver,intentFilter);

        if(!SharedPreferenceUtils.getBoolean(this,Constant.LOCKSTYLE_PWDUNLOCK,false)){
            slideView = View.inflate(getApplicationContext(), R.layout.layout_inflater,null);
        }
        pwdView = getPwdView();
        initView(slideView);
        initPwdView(pwdView);
        initData();
        initPwdData();
        lockLayer = new LockLayer(this);
        lockLayer.lock(pwdView);
        lockLayer.lock(slideView);
    }

    //初始化密码view数据
    protected void initPwdData() {}

    //初始化密码view
    protected void initPwdView(View pwdView) {}

    //获取密码界面view
    protected View getPwdView(){
        return null;
    }

    private void initData() {
        if(slideView == null){
            return;
        }
        time.setVisibility(SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_TIME,true)?View.VISIBLE:View.INVISIBLE);
        tv_date.setVisibility(SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_DATE,true)?View.VISIBLE:View.INVISIBLE);
        tv_week.setVisibility(SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_WEEK,true)?View.VISIBLE:View.INVISIBLE);
        weather.setVisibility(SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_WEATHER,true)?View.VISIBLE:View.INVISIBLE);
    }

    private void getNewTime() {
        new Thread() {
            public void run() {
                while (isTime) {
                    Date date = new Date();
                    Message msg = new Message();
                    msg.obj = date;
                    msg.what = UPDATE_TIME;
                    mHandler.sendMessage(msg);
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isTime = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isTime = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isTime = false;
        unregisterReceiver(batteryReciver);
    }


    //初始化view
    private void initView(View view){
        if(view == null){
            return;
        }
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        widthPixels = metrics.widthPixels;

        time = (TextView) view.findViewById(R.id.tv_time);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_week = (TextView) view.findViewById(R.id.tv_week);
        batteryNum = (TextView) view.findViewById(R.id.tv_battery_num);
        weather = (ImageView) view.findViewById(R.id.tv_weather);
        Date initdate = new Date();
        time.setText(MyDateUtils.getChangeTimeFormat(initdate));
        tv_date.setText(MyDateUtils.getChangeDateFormat(initdate));
        tv_week.setText( MyDateUtils.getChangeWeekFormat(initdate));
        getNewTime();
        iv_unlock = (TextView) view.findViewById(R.id.btn_unlock);
        slideUnLock((RelativeLayout) view);
    };

    private void slideUnLock(RelativeLayout view) {
        left = iv_unlock.getLeft();
        top = iv_unlock.getTop();
        right = iv_unlock.getRight();
        bottom = iv_unlock.getBottom();

        RelativeLayout view1 = view;
        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float startX = 0;
                int v1 = 0;
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        v1 = (int) (event.getX() - iv_unlock.getX());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isMoved = true;
                        int moveX = (int) (event.getX() - startX);
                        if(moveX > 0){
//                            iv_unlock.layout((int)event.getX()-v1, iv_unlock.getTop(),(int)event.getX()+iv_unlock.getWidth()-v1,iv_unlock.getBottom());
                            iv_unlock.setTranslationX(iv_unlock.getWidth()+moveX);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(isMoved){
                            float endX = event.getX();
                            float offX = endX - startX;
                            if(offX > widthPixels / 2){
                                mHandler.sendEmptyMessage(BaseLockActivity.UNLOCK_SUCCESS);
                            }
                            isMoved = false;
                        }
                        break;
                }
                return true;
            }
        });
    }


    //时间更新完成
    private void updateTimeComplete(Date date) {
        time.setText(MyDateUtils.getChangeTimeFormat(date));
        tv_date.setText(MyDateUtils.getChangeDateFormat(date));
        tv_week.setText( MyDateUtils.getChangeWeekFormat(date));
    }

    //电量改变
    private void batteryChanged(Intent intent) {
        int level = intent.getIntExtra("level", 0);
        int scale = intent.getIntExtra("scale", 100);
        if (batteryNum != null) {
            batteryNum.setText((level * 100) / scale + "%");
        }
    }

    class BatteryReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                batteryChanged(intent);
            }
        }
    }
}
