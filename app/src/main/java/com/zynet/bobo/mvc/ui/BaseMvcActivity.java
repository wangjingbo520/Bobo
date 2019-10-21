package com.zynet.bobo.mvc.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseActivity;
import com.zynet.bobo.base.IBaseView;
import com.zynet.bobo.bean.TestBean;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.volley.IHandleMessage;
import com.zynet.bobo.mvc.http.volley.MyVolleyHandler;
import com.zynet.bobo.mvc.http.volley.NetworkError;
import com.zynet.bobo.mvc.http.volley.RequestHandler;
import com.zynet.bobo.ui.widget.dialog.MessageDialog;
import com.zynet.bobo.ui.widget.statusview.SimpleMultiStateView;

import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
@SuppressLint("Registered")
public class BaseMvcActivity extends BaseActivity implements IHandleMessage, IBaseView {

    private static final String TAG = "------->";
    public MyVolleyHandler<BaseMvcActivity> mHandler;

    public SimpleMultiStateView mSimpleMultiStateView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyVolleyHandler<>(this);
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
        } else if (msg.what == MyConfig.RESPONCE_OK) {
            //请求成功回调
            String result = (String) msg.obj;
            TestBean testBean = new Gson().fromJson(result, TestBean.class);
            Log.e(TAG, testBean.getData().get(0).toString());
        }
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
     * @param interfaceMethod 接口名
     * @param code            接口标识符
     * @param params          提交的内容
     */
    public void addPostRequest(String interfaceMethod, int code, Map<String, String> params) {
        RequestHandler.addRequest(Request.Method.POST, this, mHandler, code, null, interfaceMethod, params, null, true);
    }

    public void initStateView(SimpleMultiStateView simpleMultiStateView) {
        this.mSimpleMultiStateView = simpleMultiStateView;
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.loading_dialog)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(this::onRetry);
    }

    @Override
    public void showFaild() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();

            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    @Override
    public void onRetry() {

    }


    @Override
    public void showEmptyView() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showEmptyView();
        }
    }

    @Override
    public void showContent() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }
}
