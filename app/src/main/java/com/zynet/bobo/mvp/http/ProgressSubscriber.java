package com.zynet.bobo.mvp.http;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.zynet.bobo.utils.LogUtils;
import com.zynet.bobo.utils.NetUtil;
import com.zynet.bobo.utils.ToastUtil;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ProgressSubscriber<T> implements Observer<T>, ProgressDialogListener {
    private final String networkMsg = "网络错误";
    private final String cookieOutMsg = "登录过期，请重新登录";
    private final String parseMsg = "服务器数据解析错误";
    private final String unknownMsg = "未知错误";
    private final String connectMsg = "连接服务器错误,请检查网络";
    private final String connectOutMsg = "连接服务器超时,请检查网络";

    private Context context;

    private ProgressDialogHandler mProgressDialogHandler;

    private boolean showDialog = true;

    protected ProgressSubscriber(Context context) {
        this.context = context;
        this.mProgressDialogHandler = new ProgressDialogHandler(false, this, context);
    }

    public ProgressSubscriber(Context context, boolean showDialog) {
        this.context = context;
        this.mProgressDialogHandler = new ProgressDialogHandler(false, this, context);
        this.showDialog = showDialog;
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_DIALOG)
                    .sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetUtil.isNetworkAvailable(context)) {
            ToastUtil.showMessage(connectMsg);
            return;
        }
        if (showDialog) {
            showProgressDialog();
        }
    }


    @Override
    public void onError(@NonNull Throwable e) {
        Throwable throwable = e;
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        String error = null;
        if (e instanceof ConnectException) {
            error = connectMsg;
        } else if (e instanceof HttpException) {
            //HTTP错误
            HttpException httpException = (HttpException) e;
            error = e.getLocalizedMessage() + "";
        } else if (e instanceof ApiException) {
            //服务器返回的错误
            ApiException apiException = (ApiException) e;
            switch (apiException.type) {
                case "10007":
                    error = parseMsg;
                    break;
                case "10008":
                    error = cookieOutMsg;
                    break;
                case "11111":
                    break;
                default:
                    error = e.getLocalizedMessage();
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            LogUtils.e("JsonParseException JSONException");
            error = parseMsg;
        } else if (e instanceof IOException) {
            LogUtils.e("IOException");
            if (e instanceof SocketTimeoutException) {
                LogUtils.e("SocketTimeoutException");
                error = connectOutMsg;
            } else {
                if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage())) {
                    return;
                }
                error = connectMsg;
            }
        } else {
            error = e.getLocalizedMessage();
        }

        ToastUtil.showMessage(error);

        dismissProgressDialog();
        onFinish();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        onFinish();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {

    }

    // 处理因网络请求状态异常而不能关闭列表刷新状态的问题
    public void onFinish() {
        //根据具体业务场景重写该方法以实现自己的需求
    }


}
