package com.zynet.bobo.mvc.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/23 0023
 * describe  Volley请求
 */
public class VolleyProcessor implements IHttpProcessor {

    private static RequestQueue mQueue = null;

    private static final int sTimeOut = 30000;
    //是否使用Volley默认连接超时
    private boolean timeOutDefaultFlg = false;
    private int myTimeOut = timeOutDefaultFlg ? DefaultRetryPolicy.DEFAULT_TIMEOUT_MS : sTimeOut;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<String, Object> enty : params.entrySet()) {
            hashMap.put(enty.getKey(), enty.getValue().toString());
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                callBack::onSucess, error -> callBack.onFailure()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(myTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(stringRequest);
    }

    @Override
    public void get(String url, ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                , callBack::onSucess, error -> callBack.onFailure());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(myTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(stringRequest);
    }

}
