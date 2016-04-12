package com.rcplatform.rclockscreen.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;

/**
 * Created by zuo on 2016/4/11.
 */
public class NumPwdActivity extends BaseActivity implements View.OnClickListener {
    public static final int NUMPWD_RESULTCODE = 1;
    private TextView tv_numpwd_inputnum;
    private EditText et_numpwd_password;
    private Button btn_numpwd_left;
    private Button btn_numpwd_right;
    private boolean isFirstPwd = true;
    private String password1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numpwd);
        initView();
    }

    private void initView() {
        tv_numpwd_inputnum = (TextView) findViewById(R.id.tv_numpwd_inputnum);
        et_numpwd_password = (EditText) findViewById(R.id.et_numpwd_password);
        btn_numpwd_left = (Button) findViewById(R.id.btn_numpwd_left);
        btn_numpwd_right = (Button) findViewById(R.id.btn_numpwd_right);

        btn_numpwd_left.setOnClickListener(this);
        btn_numpwd_right.setOnClickListener(this);
        btn_numpwd_right.setEnabled(false);

        et_numpwd_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lenth = s.length();
                if(isFirstPwd){
                    if(lenth == 0){
                        tv_numpwd_inputnum.setText("请输入您的数字密码");
                        btn_numpwd_right.setEnabled(false);
                    }else if(lenth < 4){
                        tv_numpwd_inputnum.setText("数字密码必须包含至少4个字符");
                        btn_numpwd_right.setEnabled(false);
                    }else if(lenth > 17){
                        tv_numpwd_inputnum.setText("数字密码必须少于17位数");
                        btn_numpwd_right.setEnabled(false);
                    }else{
                        btn_numpwd_right.setEnabled(true);
                    }
                }else{
                    tv_numpwd_inputnum.setText("确认您的密码");
                    if(lenth >= 1){
                        btn_numpwd_right.setEnabled(true);
                    }
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_numpwd_left:
                finish();
                break;
            case R.id.btn_numpwd_right:
                String password = et_numpwd_password.getText().toString().trim();
                if(isFirstPwd){
                    this.password1 = password;
                    tv_numpwd_inputnum.setText("确认您的密码");
                    et_numpwd_password.setText("");
                    isFirstPwd = false;
                }else{
                    if(password.equals(password1)){
                        SharedPreferenceUtils.putString(this, Constant.NUMPWD_KEY,password);
                        Toast.makeText(this,"设置成功",Toast.LENGTH_SHORT).show();
                        setResult(NUMPWD_RESULTCODE);
                        finish();
                    }else{
                        tv_numpwd_inputnum.setText("密码不匹配");
                        et_numpwd_password.selectAll();
                    }
                }
                break;
        }
    }
}
