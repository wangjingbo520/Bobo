package com.zynet.bobo.mvc.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zynet.bobo.bean.OnResponse;
import com.zynet.bobo.utils.ToastUtil;

import java.lang.reflect.Type;

/**
 * @author Bobo
 * @date 2019/9/22
 * describe
 */
public abstract class AbstractHttpCallBack<Result> implements ICallBack {

    public abstract void onSuccess(Result obj);

    @Override
    public void onSuccess(String result) {
        ToastUtil.showMessage("执行成功了");
    }

    private OnResponse<Result> parseJson(String jsonStr) {
        Gson gson = new Gson();
        Type jsonType = new TypeToken<OnResponse<Result>>() {
        }.getType();
        return gson.fromJson(jsonStr, jsonType);
    }

    @Override
    public void onFailure() {

    }
}
