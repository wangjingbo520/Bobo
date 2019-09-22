package com.zynet.bobo.mvc.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public abstract class HttpCallBack<Result> implements ICallBack {
    @Override
    public void onSucess(String result) {
        Gson gson = new Gson();
        Class<?> aClass = analysisClassInfo(this);
        Result obj = (Result) gson.fromJson(result, aClass);
        onSucess(obj);
    }

    @Override
    public void onFailure() {

    }

    private Class<?> analysisClassInfo(Object object) {
        Type type = object.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }

    public abstract void onSucess(Result obj);
}
