package com.rcplatform.rclockscreen.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.view.SwitchRelativeLayout;

/**
 * Created by zuo on 2016/4/7.
 */
public class LockPluginsActivity extends BaseActivity implements View.OnClickListener{

    private SwitchRelativeLayout date,time,week,weather;
    private boolean isDate;
    private boolean isTime;
    private boolean isWeek;
    private boolean isWeather;
    private RadioGroup timehour;
    private int timehourchoose;
    private RadioButton rg_lockplugins_one;
    private RadioButton rg_lockplugins_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockplugins);

        initView();
        initData();
    }

    private void initView() {
        date = (SwitchRelativeLayout) findViewById(R.id.srl_lockplugins_date);
        time = (SwitchRelativeLayout) findViewById(R.id.srl_lockplugins_time);
        week = (SwitchRelativeLayout) findViewById(R.id.srl_lockplugins_week);
        weather = (SwitchRelativeLayout) findViewById(R.id.srl_lockplugins_weather);
        rg_lockplugins_one = (RadioButton) findViewById(R.id.rg_lockplugins_one);
        rg_lockplugins_two = (RadioButton) findViewById(R.id.rg_lockplugins_two);
        timehour = (RadioGroup) findViewById(R.id.rg_lockplugins_timehour);
        timehour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == rg_lockplugins_one.getId()){
                    SharedPreferenceUtils.putInt(LockPluginsActivity.this,Constant.LOCKPLUGINS_TIMEHOUR,0);
                }else if(checkedId == rg_lockplugins_two.getId()){
                    SharedPreferenceUtils.putInt(LockPluginsActivity.this,Constant.LOCKPLUGINS_TIMEHOUR,1);
                }

            }
        });
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        week.setOnClickListener(this);
        weather.setOnClickListener(this);
    }

    private void initData() {
        isDate = SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_DATE, true);
        isTime = SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_TIME, true);
        isWeek = SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_WEEK, true);
        isWeather = SharedPreferenceUtils.getBoolean(this, Constant.LOCKPLUGINS_WEATHER, true);
        timehourchoose = SharedPreferenceUtils.getInt(this, Constant.LOCKPLUGINS_TIMEHOUR,1);
        date.setSwitchChecked(isDate);
        time.setSwitchChecked(isTime);
        week.setSwitchChecked(isWeek);
        weather.setSwitchChecked(isWeather);

        radioChecked();
        for(int i = 0;i<timehour.getChildCount();i++){
            timehour.getChildAt(i).setEnabled(isTime);
        }
    }

    private void radioChecked() {
        if(timehourchoose == 0){
            timehour.check(rg_lockplugins_one.getId());
        }else if(timehourchoose == 1){
            timehour.check(rg_lockplugins_two.getId());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.srl_lockplugins_date:
                isDate = !isDate;
                changeSate(date,isDate,Constant.LOCKPLUGINS_DATE);
                break;
            case R.id.srl_lockplugins_time:
                isTime = !isTime;
                changeSate(time,isTime,Constant.LOCKPLUGINS_TIME);
                for(int i = 0;i<timehour.getChildCount();i++){
                    timehour.getChildAt(i).setEnabled(isTime);
                }
                break;
            case R.id.srl_lockplugins_week:
                isWeek = !isWeek;
                changeSate(week,isWeek,Constant.LOCKPLUGINS_WEEK);
                break;
            case R.id.srl_lockplugins_weather:
                isWeather = !isWeather;
                changeSate(weather,isWeather,Constant.LOCKPLUGINS_WEATHER);
                break;
        }
    }

    private void changeSate(SwitchRelativeLayout srl, boolean b, String key) {
        srl.setSwitchChecked(b);
        SharedPreferenceUtils.putBoolean(this,key,b);
    }

}
