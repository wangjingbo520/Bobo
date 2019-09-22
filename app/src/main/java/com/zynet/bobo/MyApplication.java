package com.zynet.bobo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zynet.bobo.utils.ToastUtil;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        refWatcher = setupLeakCanary();
        ToastUtil.initToast(this.getApplicationContext());
    }

    public static MyApplication getContext() {
        return myApplication;
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }

}
