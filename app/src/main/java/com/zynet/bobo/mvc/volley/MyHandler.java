package com.zynet.bobo.mvc.volley;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author Bobo
 * @date 2019/10/16 0016
 * describe
 */
public class MyHandler<T extends IHandleMessage> extends Handler {

    private WeakReference<T> mTarget;

    public MyHandler(T t) {
        mTarget = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        T target = mTarget.get();
        if (target != null) {
            target.onHandleMessage(msg);
        }
    }
}