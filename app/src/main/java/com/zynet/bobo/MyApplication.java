package com.zynet.bobo;

import android.app.Application;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getContext() {
        return myApplication;
    }

}
