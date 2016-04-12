package com.rcplatform.rclockscreen.ui;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.marswin89.marsdaemon.SharedPreferenceUtils;
import com.rcplatform.rclockscreen.R;
import com.rcplatform.rclockscreen.utils.Constant;
import com.rcplatform.rclockscreen.view.PatternView;

/**
 * Created by zuo on 2016/4/11.
 */
public class PicLockActivity extends BaseLockActivity{

    private TextView tv_piclock_tip;
    private PatternView pic_lock;

    @Override
    protected void initPwdData() {
        pic_lock.setOnCompleteListener(new PatternView.OnCompleteListener() {
            @Override
            public void onComplete(String password) {
                String realPwd = SharedPreferenceUtils.getString(getApplicationContext(), Constant.PICPWD_KEY);
                if(realPwd.equals(password)){
                    mHandler.sendEmptyMessage(UNLOCK_PWD_SUCCESS);
                }else {
                    tv_piclock_tip.setText("图案不匹配，请重新绘制");
                    pic_lock.reset();
                    pic_lock.invalidate();
                }
            }

            @Override
            public void onTouchMoveing() {

            }
        });
    }

    @Override
    protected void initPwdView(View pwdView) {
        tv_piclock_tip = (TextView) pwdView.findViewById(R.id.tv_piclock_tip);
        pic_lock = (PatternView) pwdView.findViewById(R.id.pic_lock);
    }

    @Override
    protected View getPwdView() {
        Log.i("zuo","piclock启动了");
        return View.inflate(this, R.layout.activity_pic_lock,null);
    }

}
