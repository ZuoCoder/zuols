package com.rcplatform.rclockscreen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.rcplatform.rclockscreen.R;

/**
 * Created by zuo on 2016/4/7.
 */
public class SelectItemRelativeLayout extends RelativeLayout{

    private String attr_content = null;
    private View view;
    private TextView mContent;

    public SelectItemRelativeLayout(Context context) {
        this(context,null);
    }

    public SelectItemRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectItemRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.settings_switch);
        attr_content = a.getString(R.styleable.settings_switch_content);

        view = View.inflate(context, R.layout.select_item_view, null);
        intitView();
        addView(view,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        a.recycle();
    }

    private void intitView() {
        mContent = (TextView) view.findViewById(R.id.tv_setting_content);
        if(!TextUtils.isEmpty(attr_content)){
            mContent.setText(attr_content);
        }
    }

    public void setTextContent(String content){
        mContent.setText(content);
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(true);
    }
}
