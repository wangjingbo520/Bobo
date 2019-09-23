package com.zynet.bobo.mvc.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zynet.bobo.mvc.http.HttpCallBack;
import com.zynet.bobo.mvc.http.HttpHelper;

import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
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
