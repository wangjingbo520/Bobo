package com.zynet.bobo.mvc.ui;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zynet.bobo.base.BaseActivity;
import com.zynet.bobo.mvc.volley.IHandleMessage;
import com.zynet.bobo.mvc.volley.MyHandler;
import com.zynet.bobo.mvc.volley.network.NetworkError;
import com.zynet.bobo.ui.widget.dialog.MessageDialog;

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
        if (msg.what == NetworkError.NET_ERROR_CUSTOM) {
            MessageDialog dialog = new MessageDialog(this);
            dialog.setMessage("获取请求失败了");
            dialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // startActivity(new Intent(this, HttpsTestActivity.class));
                }
            });
            dialog.show();
        }

    }

}
