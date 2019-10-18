package com.zynet.bobo.mvc.ui;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.android.volley.Request;
import com.zynet.bobo.base.BaseActivity;
import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.mvc.volley.IHandleMessage;
import com.zynet.bobo.mvc.volley.MyHandler;
import com.zynet.bobo.mvc.volley.network.NetworkError;
import com.zynet.bobo.mvc.volley.network.RequestHandler;
import com.zynet.bobo.ui.widget.dialog.MessageDialog;

import java.util.HashMap;
import java.util.Map;

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
            dialog.setMessage("请求服务器失败了");
            dialog.setOnClickListener(v -> {
                // startActivity(new Intent(this, HttpsTestActivity.class));
            });
            dialog.show();
        } else {
            String result = (String) msg.obj;
        }
    }

    public void onSucess(String InterfaceMethod,String request){

    }

    /**
     * get请求
     *
     * @param interfaceMethod    接口名
     * @param RESULT_GET_IP_INFO 接口标识符
     */
    public void addGetRequest(String interfaceMethod, int RESULT_GET_IP_INFO) {
        RequestHandler.addRequest(Request.Method.GET, this, mHandler, RESULT_GET_IP_INFO, null, interfaceMethod, null, null, true);
    }


    /**
     * post 请求
     *
     * @param interfaceMethod    接口名
     * @param RESULT_GET_IP_INFO 接口标识符
     * @param params             提交的内容
     */
    public void addPostRequest(String interfaceMethod, int RESULT_GET_IP_INFO, Map<String, String> params) {
        RequestHandler.addRequest(Request.Method.POST, this, mHandler, RESULT_GET_IP_INFO, null, interfaceMethod, params, null, true);
    }

}
