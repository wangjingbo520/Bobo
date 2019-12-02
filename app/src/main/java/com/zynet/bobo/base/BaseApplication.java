package com.zynet.bobo.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe 启动页优化/第三方一些初始化
 */
@SuppressLint("Registered")
public class BaseApplication extends Application {

    private static final String TAG = "lxb-MyApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

//    private boolean isMainProcess(Context context) {
//        return context.getPackageName().equals(SystemUtil.getProcessName(context));
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        if (!isMainProcess(this)) {
//            Log.d(TAG, "onCreate: 非主进程，return");
//            return;
//        }

        Log.d(TAG, "主进程 onCreate: 一些初始化操作");
    }

}
