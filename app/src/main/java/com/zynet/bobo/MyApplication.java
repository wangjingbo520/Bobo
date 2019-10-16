package com.zynet.bobo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        mRequestQueue = Volley.newRequestQueue(this);
    }

    public static MyApplication getContext() {
        return myApplication;
    }

}
