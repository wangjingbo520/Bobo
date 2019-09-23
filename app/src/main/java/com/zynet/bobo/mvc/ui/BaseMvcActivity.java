package com.zynet.bobo.mvc.http;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Map;

public abstract class BaseMvcActivity<T> extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHttpType();
    }

    public void doGetRequest(String url) {
        HttpHelper.getInstance().get(url, new HttpCallBack<T>() {
            @Override
            public void onSucess(T obj) {
                onNetSuccess(url, obj);
            }
        });
    }

    public void doPostRequest(String url, Map<String, Object> params) {
        HttpHelper.getInstance().post(url, params, new HttpCallBack<T>() {
            @Override
            public void onSucess(T obj) {
                onNetSuccess(url, obj);
            }
        });

    }


    public abstract void onNetSuccess(String url, T obj);

    public abstract void initHttpType();


}
