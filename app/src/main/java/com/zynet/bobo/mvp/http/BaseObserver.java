package com.zynet.bobo.mvp.http;

import com.google.gson.JsonParseException;
import com.zynet.bobo.utils.LogUtils;
import com.zynet.bobo.utils.ToastUtil;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private final String networkMsg = "网络错误";
    private final String cookieOutMsg = "登录过期，请重新登录";
    private final String parseMsg = "服务器数据解析错误";
    private final String unknownMsg = "未知错误";
    private final String connectMsg = "连接服务器错误,请检查网络";
    private final String connectOutMsg = "连接服务器超时,请检查网络";

    /**
     * 请求成功的回调
     *
     * @param t 返回的数据
     */
    public abstract void onSuccess(T t);

    /**
     * @param error          错误信息
     * @param isNetWorkError 是否是网络错误
     * @throws Exception 异常捕获
     */
    protected abstract void onFailure(String error, boolean isNetWorkError) throws Exception;

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
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

        try {
            ToastUtil.showMessage(error);
            onFailure(error, false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }
}

