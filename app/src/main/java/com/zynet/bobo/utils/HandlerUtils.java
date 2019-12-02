package com.zynet.bobo.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author wjb
 * describe
 */
public class HandlerUtils {

    private HandlerUtils() {
        throw new UnsupportedOperationException("不能直接创建");
    }

    public static class HandlerReference extends Handler {

        private final WeakReference<Object> mObject;
        private OnReceiveMessageListener mListener;


        public HandlerReference(Object object, OnReceiveMessageListener listener) {
            this.mObject = new WeakReference<>(object);
            this.mListener = listener;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object object = mObject.get();
            if (object != null && mListener != null) {
                //处理逻辑
                mListener.handlerMessage(msg);
            }
        }

        /**
         * 收到消息回调接口
         */
        public interface OnReceiveMessageListener {
            /**
             * 消息处理
             *
             * @param msg msg
             */
            void handlerMessage(Message msg);
        }
    }
}
