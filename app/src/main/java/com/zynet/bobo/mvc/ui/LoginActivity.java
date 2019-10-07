package com.zynet.bobo.mvc.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.zynet.bobo.R;
import com.zynet.bobo.mvc.http.HttpHelper;
import com.zynet.bobo.mvc.http.VolleyProcessor;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class LoginActivity extends BaseMvcActivity {

    String url = "https://wanandroid.com/wxarticle/chapters/json";
    @BindView(R.id.tvConent)
    TextView tvConent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        doGetRequest(url);
    }

    @Override
    public void onNetSuccess(String url, String obj) {
        tvConent.setText(obj);
    }


    @Override
    public void initHttpType() {
        HttpHelper.init(new VolleyProcessor(this));
    }
}
