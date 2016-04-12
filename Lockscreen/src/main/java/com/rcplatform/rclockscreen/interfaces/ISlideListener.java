package com.rcplatform.rclockscreen.interfaces;

/**
 * Created by zuo on 2016/4/5.
 */
public interface ISlideListener {
    void slideDown(float startX);
    void slideMove(float offX);
    void slideUp(float offX);
}
