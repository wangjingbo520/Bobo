package com.zynet.bobo.mvc.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.zynet.bobo.bean.OnResponse;
import com.zynet.bobo.bean.BannerBean;
import com.zynet.bobo.mvc.http.AbstractHttpCallBack;
import com.zynet.bobo.mvc.http.HttpHelper;
import com.zynet.bobo.utils.ToastUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public abstract class BaseMvcActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHttpType();
    }

    public void doGetRequest(String url) {
        HttpHelper.getInstance().get(url, new AbstractHttpCallBack<OnResponse<List<BannerBean>>>() {
            @Override
            public void onSucess(OnResponse<List<BannerBean>> obj) {
                ToastUtil.showMessage(obj.data.get(0).getName());
                Log.i("------>数据是", obj.data.get(0).getName());
                onNetSuccess(url, obj.data.get(0).getName());
            }
        });
    }

    public void doPostRequest(String url, Map<String, Object> params) {
        HttpHelper.getInstance().post(url, params, new AbstractHttpCallBack<BannerBean>() {
            @Override
            public void onSucess(BannerBean obj) {
                //  onNetSuccess(url, obj);
            }
        });

    }


    public abstract void onNetSuccess(String url, String obj);

    public abstract void initHttpType();


}
