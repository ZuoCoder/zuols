package com.rcplatform.rclockscreen.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zuo on 2016/4/1.
 */
@SuppressLint("SimpleDateFormat")
public class MyDateUtils {

    /**
     * 转换后的日期
     */
    public static String getChangeDateFormat(Date date){
        String str = null;
        if(date != null && !"".equals(date)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            str = format.format(date);
        }
        return str;
    }

    /**
     *转换后的时间
     */
    public static String getChangeTimeFormat(Date date){
        String str = null;
        if(date != null && !"".equals(date)){
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            str = format.format(date);
        }
        return str;
    }

    public static String getChangeWeekFormat(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        return weeks[week];
    }
}
