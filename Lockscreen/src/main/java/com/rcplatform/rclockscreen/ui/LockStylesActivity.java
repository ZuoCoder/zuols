package com.rcplatform.rclockscreen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.view.CheckedRelativeLayout;

/**
 * Created by zuo on 2016/4/7.
 */
public class LockStylesActivity extends BaseActivity implements View.OnClickListener{
    private CheckedRelativeLayout crl_lockstyle_nopwd;
    private CheckedRelativeLayout crl_lockstyle_pinpwd;
    private CheckedRelativeLayout crl_lockstyle_picpwd;
    private CheckedRelativeLayout crl_lockstyle_numpwd;
    private CheckedRelativeLayout crl_lockstyle_pathvisiable;
    private CheckedRelativeLayout crl_lockstyle_pwdunlock;
    private boolean path_visiable;
    private boolean pwd_unlock;
    private static final int PICPWD_REQUSTCODE = 0;//图案解锁请求码
    private static final int NUMPWD_REQUSTCODE = 1;//数字解锁请求码
    private static final int NOPWD = 1;
    private static final int PINPWD = 2;
    private static final int PICPWD = 3;
    private static final int NUMPWD = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockstyle);
        initView();
        initData();
    }

    private void initView() {
        crl_lockstyle_nopwd = (CheckedRelativeLayout) findViewById(R.id.crl_lockstyle_nopwd);
        crl_lockstyle_pinpwd = (CheckedRelativeLayout) findViewById(R.id.crl_lockstyle_pinpwd);
        crl_lockstyle_picpwd = (CheckedRelativeLayout) findViewById(R.id.crl_lockstyle_picpwd);
        crl_lockstyle_numpwd = (CheckedRelativeLayout) findViewById(R.id.crl_lockstyle_numpwd);
        crl_lockstyle_pathvisiable = (CheckedRelativeLayout) findViewById(R.id.crl_lockstyle_pathvisiable);
        crl_lockstyle_pwdunlock = (CheckedRelativeLayout) findViewById(R.id.crl_lockstyle_pwdunlock);
        crl_lockstyle_nopwd.setOnClickListener(this);
        crl_lockstyle_pinpwd.setOnClickListener(this);
        crl_lockstyle_picpwd.setOnClickListener(this);
        crl_lockstyle_numpwd.setOnClickListener(this);
        crl_lockstyle_pathvisiable.setOnClickListener(this);
        crl_lockstyle_pwdunlock.setOnClickListener(this);
    }

    private void initData() {
        int styleId = SharedPreferenceUtils.getInt(this, Constant.LOCKSTYLE_TYPLES, 1);
        path_visiable = SharedPreferenceUtils.getBoolean(this, Constant.LOCKSTYLE_PATHVISIABLE, true);
        pwd_unlock = SharedPreferenceUtils.getBoolean(this, Constant.LOCKSTYLE_PWDUNLOCK, false);
        setSelectItem(styleId);
        crl_lockstyle_pathvisiable.setBoxChecked(path_visiable);
        crl_lockstyle_pwdunlock.setBoxChecked(pwd_unlock);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.crl_lockstyle_nopwd:
                setSelectItem(NOPWD);
                break;
            case R.id.crl_lockstyle_pinpwd:
                setSelectItem(PINPWD);
                break;
            case R.id.crl_lockstyle_picpwd:
                startActivityForResult(new Intent(this,PicPwdActivity.class),PICPWD_REQUSTCODE);
                break;
            case R.id.crl_lockstyle_numpwd:
                startActivityForResult(new Intent(this,NumPwdActivity.class),NUMPWD_REQUSTCODE);
                break;
            case R.id.crl_lockstyle_pathvisiable:
                path_visiable = !path_visiable;
                crl_lockstyle_pathvisiable.setBoxChecked(path_visiable);
                SharedPreferenceUtils.putBoolean(this,Constant.LOCKSTYLE_PATHVISIABLE,path_visiable);
                break;
            case R.id.crl_lockstyle_pwdunlock:
                pwd_unlock = !pwd_unlock;
                crl_lockstyle_pwdunlock.setBoxChecked(!pwd_unlock);
                SharedPreferenceUtils.putBoolean(this,Constant.LOCKSTYLE_PWDUNLOCK,!pwd_unlock);
                break;
        }
    }

    private void setSelectItem(int styleId) {
        crl_lockstyle_nopwd.setBoxChecked(styleId==1);
        crl_lockstyle_pinpwd.setBoxChecked(styleId==2);
        crl_lockstyle_picpwd.setBoxChecked(styleId==3);
        crl_lockstyle_numpwd.setBoxChecked(styleId==4);
        SharedPreferenceUtils.putInt(this,Constant.LOCKSTYLE_TYPLES,styleId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICPWD_REQUSTCODE && resultCode == PicPwdActivity.PICPWD_RESULTCODE){
            setSelectItem(PICPWD);
        }
        if(requestCode == NUMPWD_REQUSTCODE && resultCode == NumPwdActivity.NUMPWD_RESULTCODE){
            setSelectItem(NUMPWD);
        }
    }
}
