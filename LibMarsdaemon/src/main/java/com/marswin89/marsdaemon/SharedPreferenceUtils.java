package com.marswin89.marsdaemon;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zuo on 2016/3/29.
 */
public class SharedPreferenceUtils {

    private static String SHAREPRE = "sharePre";
    private static SharedPreferences sp = null;

    public static boolean putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRE, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        return edit.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRE, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static boolean putString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRE, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        return edit.commit();
    }

    public static String getString(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRE, Context.MODE_PRIVATE);
        }
        return sp.getString(key,"");
    }

    public static boolean putInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRE, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        return edit.commit();
    }

    public static int getInt(Context context, String key,int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRE, Context.MODE_PRIVATE);
        }
        return sp.getInt(key,defValue);
    }
}
