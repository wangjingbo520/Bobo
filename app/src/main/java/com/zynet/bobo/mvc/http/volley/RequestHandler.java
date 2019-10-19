package com.zynet.bobo.mvc.http.volley;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zynet.bobo.MyApplication;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.ui.widget.dialog.LoadingDialog;
import com.zynet.bobo.utils.LogUtil;
import com.zynet.bobo.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe Volley请求工具类封装
 */
public class RequestHandler {

    /**
     * 开始请求
     *
     * @param method  Request.Method.GET 或 Request.Method.POST
     * @param handler 请求结束后将结果作为Message.obj发送到该Handler
     * @param what    请求结束后发送的Message.what
     * @param bundle  不参与网络请求，仅携带参数
     *                （请求结束后，通过Message.setData设置到Message对象，数据原样返回）
     * @param url     请求地址
     * @param params  请求参数
     * @param header  请求头
     */
    public static void addRequest(
            final int method, Context context, final Handler handler, final int what, final Bundle bundle,
            final String url, final Map<String, String> params, final Map<String, String> header, boolean isShowLoadingDialog) {
        if (isShowLoadingDialog) {
            addRequest(method, handler, what, bundle, url, params, header, new DefaultDialogRequestListener(context));
        } else {
            addRequest(method, handler, what, bundle, url, params, header, new DefaultRequestListener());
        }

    }

    /**
     * @param method   Request.Method.GET 或 Request.Method.POST
     * @param handler  传递消息
     * @param what     message.what
     * @param bundle   请求时候携带的数据
     * @param url      请求地址
     * @param params   请求携带参数
     * @param header   请求头
     * @param listener 请求回调监听
     */
    private static void addRequest(
            int method,
            final Handler handler, final int what,
            final Bundle bundle, String url, final Map<String, String> params, final Map<String, String> header,
            final NetWorkRequestListener listener) {
        if (method == Request.Method.GET) {
            url = NetworkHelper.getUrlWithParams(url, params);
        }
        listener.onPreRequest();
        StringRequest request = new StringRequest(method, MyConfig.BASE_URL + url, response -> {

            onVolleyResponse(response, handler, what, bundle);
            listener.onResponse();
        }, volleyError -> {
            listener.onFailed();
            onVolleyErrorResponse(volleyError, handler);
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = header;
                if (map == null) {
                    map = new HashMap<>();
                }
                // 在此统一添加header
                //   map.put("versionName", BuildConfig.VERSION_NAME);
                return map;
            }

            /**
             * Volley仅在post的情况下会回调该方法，获取form表单参数
             * @return map
             * @throws AuthFailureError 异常
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                //连接超时设置
                10 * 1000,
                //重新尝试连接次数
                3,
                //曲线增长因子
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        RequestManager.getInstance(MyApplication.getContext()).getRequestQueue().add(request);
    }


    /**
     * 请求成功的回调
     *
     * @param response 网络返回的数据
     * @param handler  请求结束后将结果作为Message.obj发送到该Handler
     * @param what     请求结束后发送的Message.what
     * @param bundle   不参与网络请求，仅携带参数
     *                 （请求结束后，通过Message.setData设置到Message对象，数据原样返回）
     */
    private static void onVolleyResponse(String response, Handler handler, int what, Bundle bundle) {
        LogUtil.d(response);
        try {
            JSONObject json = JSON.parseObject(response);
            if (json != null && json.containsKey("code")) {
                int code = json.getIntValue("code");
                if (code != 0) {
                    // 如果code不为0，则走错误处理流程
                    Message msg = handler.obtainMessage(NetworkError.NET_ERROR_CUSTOM);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    NetworkError.error("" + code, json, bundle);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        Message msg = handler.obtainMessage(what, response);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }


    private static void onVolleyErrorResponse(VolleyError error, Handler handler) {
        if (error instanceof NoConnectionError || error instanceof com.android.volley.NetworkError) {
            ToastUtil.showMessage("网络链接异常");
            handler.sendEmptyMessage(0);
        } else if (error instanceof TimeoutError) {
            ToastUtil.showMessage("连接超时");
            handler.sendEmptyMessage(1);
        } else if (error instanceof AuthFailureError) {
            ToastUtil.showMessage("身份验证失败！");
            handler.sendEmptyMessage(2);
        } else if (error instanceof ParseError) {
            handler.sendEmptyMessage(3);
            ToastUtil.showMessage("解析错误！");
        } else if (error instanceof ServerError) {
            ToastUtil.showMessage("服务器响应错误！");
            handler.sendEmptyMessage(4);
        }
    }


    /**
     * 请求过程中显示加载对话框，且自动处理其生命周期
     */
    private static class DefaultDialogRequestListener extends DefaultRequestListener {

        Context context;
        LoadingDialog dialog;

        private DefaultDialogRequestListener(Context context) {
            this.context = context;
            dialog = new LoadingDialog(context);
        }

        @Override
        public void onPreRequest() {
            dialog.show();
        }

        @Override
        public void onResponse() {
            dialog.dismiss();
        }

        @Override
        public void onFailed() {
            dialog.dismiss();
        }
    }


    /**
     * 请求过程中没有加载进度框
     */
    private static class DefaultRequestListener implements NetWorkRequestListener {


        @Override
        public void onPreRequest() {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onFailed() {

        }

    }

    /**
     * 用于所有网络请求，在不同时机回调的接口
     */
    private interface NetWorkRequestListener {
        void onPreRequest();

        void onResponse();

        void onFailed();

    }
}
