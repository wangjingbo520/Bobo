package com.zynet.bobo.mvc.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zynet.bobo.MyApplication;

import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/23 0023
 * describe  Volley请求
 */
public class VolleyProcessor implements IHttpProcessor {

    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                callBack::onSucess, error -> callBack.onFailure());
        mQueue.add(stringRequest);
    }

    @Override
    public void get(String url) {

    }

//    public void removeRequest(){
//        if (mQueue!=null||)
//        MyApplication.getRequestQueue().cancelAll(VOLLEY_TAG);
//    }
}
