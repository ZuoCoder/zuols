package com.rcplatform.rclockscreen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.rcplatform.rclockscreen.R;

/**
 * Created by zuo on 2016/4/7.
 */
public class SwitchRelativeLayout extends RelativeLayout{

    private String attr_content = null;
    private boolean attr_checked = false;
    private View view;
    private TextView mContent;
    private Switch mSwitch;

    public SwitchRelativeLayout(Context context) {
        this(context,null);
    }

    public SwitchRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwitchRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.settings_switch);
        attr_content = a.getString(R.styleable.settings_switch_content);
        attr_checked = a.getBoolean(R.styleable.settings_switch_selected, false);

        view = View.inflate(context, R.layout.switch_view, null);
        intitView();
        addView(view,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        a.recycle();
    }

    private void intitView() {
        mContent = (TextView) view.findViewById(R.id.tv_setting_content);
        mSwitch = (Switch)view.findViewById(R.id.sw_setting_switch);
        mSwitch.setChecked(attr_checked);
        if(!TextUtils.isEmpty(attr_content)){
            mContent.setText(attr_content);
        }
    }

    public void setSwitchChecked(boolean isChecked){
        mSwitch.setChecked(isChecked);
    }

    public void setTextContent(String content){
        mContent.setText(content);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(true);
    }
}
