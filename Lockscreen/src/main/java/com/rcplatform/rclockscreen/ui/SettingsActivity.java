package com.rcplatform.rclockscreen.ui;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.view.SelectItemRelativeLayout;
import com.rcplatform.rclockscreen.view.SwitchRelativeLayout;

/**
 * Created by zuo on 2016/4/7.
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener{


    private SwitchRelativeLayout msgRemind;
    private SelectItemRelativeLayout chooseApp,lockPlugins,lockStyles;
    private boolean isMsgRemind;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";//权限设置界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initActionBar();
        initView();
        initData();
    }

    private void initActionBar() {
//        ActionBar actionBar = getActionBar();
//        actionBar.setTitle("设置");
    }

    private void initView() {
        msgRemind = (SwitchRelativeLayout) findViewById(R.id.srl_msg_switch);
        chooseApp = (SelectItemRelativeLayout) findViewById(R.id.sirl_chose_app);
        lockPlugins = (SelectItemRelativeLayout) findViewById(R.id.sirl_lockscreen_plugins);
        lockStyles = (SelectItemRelativeLayout) findViewById(R.id.sirl_lockscreen_styles);
        msgRemind.setOnClickListener(this);
        chooseApp.setOnClickListener(this);
        lockPlugins.setOnClickListener(this);
        lockStyles.setOnClickListener(this);
    }

    private void initData(){
        isMsgRemind = SharedPreferenceUtils.getBoolean(this, Constant.MSG_REMIND, false);
        msgRemind.setSwitchChecked(isMsgRemind);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.srl_msg_switch:
                messageRemind();
                break;
            case R.id.sirl_chose_app:
                Toast.makeText(this,"sirl_chose_app点击了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sirl_lockscreen_plugins:
                startActivity(new Intent(this,LockPluginsActivity.class));
//                Toast.makeText(this,"sirl_lockscreen_plugins点击了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sirl_lockscreen_styles:
                startActivity(new Intent(this,LockStylesActivity.class));
//                Toast.makeText(this,"sirl_lockscreen_styles点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void messageRemind() {
        if(isEnabledNotifaListener()){
            isMsgRemind = !isMsgRemind;
            msgRemind.setSwitchChecked(isMsgRemind);
            SharedPreferenceUtils.putBoolean(this,Constant.MSG_REMIND,isMsgRemind);
        }else{
            showDialog();
        }

    }

    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请打开消息权限");
        builder.setMessage("是否现在打开？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
            }
        });
        builder.show();
    }

    private boolean isEnabledNotifaListener() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
