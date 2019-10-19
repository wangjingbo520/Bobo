package com.zynet.bobo.mvc.http.proxyhttp;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;

import me.jessyan.autosize.utils.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class OkHttpProcessor implements IHttpProcessor {
    private OkHttpClient okHttpClient;
    private android.os.Handler mHandler;

    public OkHttpProcessor() {
        okHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        RequestBody requestBody = appenBody(params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        return;
                    }
                    String result = response.body().string();
                    LogUtils.e(result);
                    mHandler.post(() -> callBack.onSuccess(result));
                }
            }
        });
    }

    private RequestBody appenBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> enty : params.entrySet()) {
            body.add(enty.getKey(), enty.getValue().toString());
        }
        return body.build();
    }

    @Override
    public void get(String url, ICallBack callBack) {
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        return;
                    }
                    String result = response.body().string();
                    LogUtils.e(result);
                    mHandler.post(() -> callBack.onSuccess(result));

                }

            }
        });
    }
}
