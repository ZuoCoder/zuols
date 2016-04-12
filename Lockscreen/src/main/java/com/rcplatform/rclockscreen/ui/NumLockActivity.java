package com.rcplatform.rclockscreen.ui;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.utils.MyDateUtils;

import java.util.Date;

/**
 * Created by zuo on 2016/4/5.
 */
public class NumLockActivity extends BaseLockActivity implements View.OnClickListener{

    private TextView tv_numlock_tip;
    private Button numlock_btn01;
    private Button numlock_btn02;
    private Button numlock_btn03;
    private Button numlock_btn04;
    private Button numlock_btn05;
    private Button numlock_btn06;
    private Button numlock_btn07;
    private Button numlock_btn08;
    private Button numlock_btn09;
    private ImageButton numlock_btnback;
    private Button numlock_btn00;
    private ImageButton numlock_btnclear;
    private LinearLayout keyboard;
    private EditText et_numlock_password;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void initPwdData() {
        et_numlock_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String realPwd = SharedPreferenceUtils.getString(getApplicationContext(), Constant.NUMPWD_KEY);
                if(realPwd.equals(s)){
                    mHandler.sendEmptyMessage(UNLOCK_PWD_SUCCESS);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initPwdView(View pwdView) {
        tv_numlock_tip = (TextView) pwdView.findViewById(R.id.tv_numlock_tip);
        numlock_btn01 = (Button) pwdView.findViewById(R.id.numlock_btn01);
        numlock_btn02 = (Button) pwdView.findViewById(R.id.numlock_btn02);
        numlock_btn03 = (Button) pwdView.findViewById(R.id.numlock_btn03);
        numlock_btn04 = (Button) pwdView.findViewById(R.id.numlock_btn04);
        numlock_btn05 = (Button) pwdView.findViewById(R.id.numlock_btn05);
        numlock_btn06 = (Button) pwdView.findViewById(R.id.numlock_btn06);
        numlock_btn07 = (Button) pwdView.findViewById(R.id.numlock_btn07);
        numlock_btn08 = (Button) pwdView.findViewById(R.id.numlock_btn08);
        numlock_btn09 = (Button) pwdView.findViewById(R.id.numlock_btn09);
        numlock_btnback = (ImageButton) pwdView.findViewById(R.id.numlock_btnback);
        numlock_btn00 = (Button) pwdView.findViewById(R.id.numlock_btn00);
        numlock_btnclear = (ImageButton) pwdView.findViewById(R.id.numlock_btnclear);
        et_numlock_password = (EditText) pwdView.findViewById(R.id.et_numlock_password);
        keyboard = (LinearLayout) pwdView.findViewById(R.id.keyboard);

        numlock_btn01.setOnClickListener(this);
        numlock_btn02.setOnClickListener(this);
        numlock_btn03.setOnClickListener(this);
        numlock_btn04.setOnClickListener(this);
        numlock_btn05.setOnClickListener(this);
        numlock_btn06.setOnClickListener(this);
        numlock_btn07.setOnClickListener(this);
        numlock_btn08.setOnClickListener(this);
        numlock_btn09.setOnClickListener(this);
        numlock_btnback.setOnClickListener(this);
        numlock_btn00.setOnClickListener(this);
        numlock_btnclear.setOnClickListener(this);
    }

    @Override
    protected View getPwdView() {
        Log.i("zuo","numlock启动了");
        return View.inflate(this,R.layout.activity_numlock,null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.numlock_btn01:
                setEditTextPassword(1);
                break;
            case R.id.numlock_btn02:
                setEditTextPassword(2);
                break;
            case R.id.numlock_btn03:
                setEditTextPassword(3);
                break;
            case R.id.numlock_btn04:
                setEditTextPassword(4);
                break;
            case R.id.numlock_btn05:
                setEditTextPassword(5);
                break;
            case R.id.numlock_btn06:
                setEditTextPassword(6);
                break;
            case R.id.numlock_btn07:
                setEditTextPassword(7);
                break;
            case R.id.numlock_btn08:
                setEditTextPassword(8);
                break;
            case R.id.numlock_btn09:
                setEditTextPassword(9);
                break;
            case R.id.numlock_btnback:
                mHandler.sendEmptyMessage(UNLOCK_PWD_SUCCESS);
                break;
            case R.id.numlock_btn00:
                setEditTextPassword(0);
                break;
            case R.id.numlock_btnclear:
                String str = et_numlock_password.getText().toString();
                et_numlock_password.setText(str.substring(0,et_numlock_password.length()-1));
                break;
        }
    }

    public void setEditTextPassword(int s){
        String input = et_numlock_password.getText().toString();
        input += s;
        et_numlock_password.setText(input);
    }
}
