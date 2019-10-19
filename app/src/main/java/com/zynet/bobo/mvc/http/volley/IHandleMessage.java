package com.zynet.bobo.mvc.http.volley;

import android.os.Message;

/**
 * @author Bobo
 * @date 2019/10/16
 * describe
 */
public interface IHandleMessage {
    /**
     * volley回调
     *
     * @param msg message
     */
    void onHandleMessage(Message msg);
}