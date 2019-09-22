package com.zynet.bobo.mvc.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zynet.bobo.mvc.http.HttpHelper;
import com.zynet.bobo.mvc.http.OkHttpProcessor;

public class LoginActivity extends AppCompatActivity {

    String url = "https://wanandroid.com/wxarticle/chapters/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.getInstance().get(url);
    }
}
