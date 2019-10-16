package com.zynet.bobo.utils;

import android.util.Log;

import com.zynet.bobo.BuildConfig;
import com.zynet.bobo.MyApplication;
import com.zynet.bobo.R;


/**
 * Created by joyin on 16-4-3.
 */
public class LogUtil {
    private LogUtil() {

    }

    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final String TAG = MyApplication.getContext().getString(R.string.config_logcat_tag);


    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void exception(String msg) {
        e(msg);
    }
}
