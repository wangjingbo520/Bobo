package com.zynet.bobo.mvc.http.volley;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author Bobo
 * @date 2019/10/16 0016
 * describe
 */
public class MyVolleyHandler<T extends IHandleMessage> extends Handler {

    private WeakReference<T> mTarget;

    public MyVolleyHandler(T t) {
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