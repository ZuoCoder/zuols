package com.rcplatform.rclockscreen.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.services.Service1;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Intent intent;
    private Button startServer;
    private Button stopServer;
    private Button setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, Service1.class);

        startServer = (Button) findViewById(R.id.btn_start_server);
        stopServer = (Button) findViewById(R.id.btn_stop_server);
        setting = (Button) findViewById(R.id.btn_setting);
        startServer.setOnClickListener(this);
        stopServer.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_server:
                SharedPreferenceUtils.putBoolean(getApplicationContext(),"UserClose",false);
                startService(intent);
                Toast.makeText(getApplicationContext(),"服务开启了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_stop_server:
                SharedPreferenceUtils.putBoolean(getApplicationContext(),"UserClose",true);
                stopService(intent);
                Toast.makeText(getApplicationContext(),"服务关闭了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_setting:
                startActivity(new Intent(this,SettingsActivity.class));
                break;
        }
    }

}
