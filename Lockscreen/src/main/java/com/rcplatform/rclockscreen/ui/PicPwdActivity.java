package com.rcplatform.rclockscreen.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.view.PatternView;

/**
 * Created by zuo on 2016/4/8.
 */
public class PicPwdActivity extends BaseActivity implements View.OnClickListener {
    public static final int PICPWD_RESULTCODE = 4;//图案结果码
    private TextView tv_picpwd_tips;
    private Button btn_picpwd_left;
    private Button btn_picpwd_right;
    private PatternView lock;
    private String password1;
    private int leftType = CANCLE;//左侧button类型
    private static final int CANCLE = 0;//取消
    private static final int RETRY = 1;//重试

    private int rightType = CONTINUE;//左侧button类型
    private static final int CONTINUE = 2;//继续
    private static final int OK = 3;//确定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picpwd);
        initView();

    }

    private void initView() {
        tv_picpwd_tips = (TextView) findViewById(R.id.tv_picpwd_tips);
        lock = (PatternView) findViewById(R.id.lock);
        btn_picpwd_left = (Button) findViewById(R.id.btn_picpwd_left);
        btn_picpwd_right = (Button) findViewById(R.id.btn_picpwd_right);

        btn_picpwd_left.setOnClickListener(this);
        btn_picpwd_right.setOnClickListener(this);
        btn_picpwd_right.setEnabled(false);
        lock.setOnCompleteListener(new PatternView.OnCompleteListener() {

            @Override
            public void onComplete(String password) {
                if(rightType == CONTINUE){
                    password1 = password;
                    tv_picpwd_tips.setText("图案已记录！");
                    btn_picpwd_left.setText("重试");
                    btn_picpwd_right.setEnabled(true);
                    leftType = RETRY;
                }else if(rightType == OK){
                    if(password1.equals(password)){
                        btn_picpwd_right.setEnabled(true);
                    }else{
                        Toast.makeText(getApplicationContext(),"两次密码不一致",Toast.LENGTH_SHORT).show();
                        tv_picpwd_tips.setText("请重试：");
                        lock.reset();
                        lock.invalidate();
                    }
                }
            }

            @Override
            public void onTouchMoveing() {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_picpwd_left:
                if(leftType == CANCLE){
                    finish();
                }else if(leftType == RETRY){
                    btn_picpwd_left.setText("取消");
                    btn_picpwd_right.setEnabled(false);
                    tv_picpwd_tips.setText("绘制图案密码");
                    leftType = CANCLE;
                    lock.reset();
                    lock.invalidate();
                }
                break;
            case R.id.btn_picpwd_right:
                if(rightType == CONTINUE){
                    tv_picpwd_tips.setText("请再次绘制以去人图案：");
                    btn_picpwd_left.setText("取消");
                    btn_picpwd_right.setText("确认");
                    btn_picpwd_right.setEnabled(false);
                    leftType = CANCLE;
                    rightType = OK;
                    lock.reset();
                    lock.invalidate();
                }else if(rightType == OK){
                    SharedPreferenceUtils.putString(getApplicationContext(), Constant.PICPWD_KEY,password1);
                    Toast.makeText(getApplicationContext(),"设置成功",Toast.LENGTH_SHORT).show();
                    setResult(PICPWD_RESULTCODE);
                    finish();
                }
                break;
        }
    }
}
