package com.rcplatform.rclockscreen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rcplatform.rclockscreen.interfaces.ISlideListener;

/**
 * Created by zuo on 2016/4/5.
 */
public class SlideRelativeLayout extends RelativeLayout{

    private ISlideListener slideListener = null;
    private int widthPixels;
    private ImageView imageView;
    private int slideIconLeft;

    public SlideRelativeLayout(Context context) {
        this(context,null);
    }

    public SlideRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SlideRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        widthPixels = metrics.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float startX = 0;

        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();

                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                float offX = endX - startX;
                if(offX > widthPixels / 2){

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setOnSlideListener(ISlideListener slideListener){
        this.slideListener = slideListener;
    }

    public void setImageView(ImageView img){
        this.imageView = img;
        slideIconLeft = imageView.getLeft();
    }
}
