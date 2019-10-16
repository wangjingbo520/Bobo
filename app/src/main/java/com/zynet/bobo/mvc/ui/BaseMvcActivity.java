package com.zynet.bobo.mvc.ui;

import android.os.Bundle;
import android.os.Message;

import com.zynet.bobo.base.BaseActivity;
import com.zynet.bobo.mvc.volley.IHandleMessage;
import com.zynet.bobo.mvc.volley.MyHandler;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public abstract class BaseMvcActivity extends BaseActivity implements IHandleMessage {

    public MyHandler<BaseMvcActivity> mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyHandler<>(this);
    }

    @Override
    public void onHandleMessage(Message msg) {


    }

}
