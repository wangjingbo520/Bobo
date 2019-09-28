package com.zynet.bobo.mvc.http;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public abstract class HttpCallBack<T> implements ICallBack {

    @Override
    public void onSucess(String result) {
        Type genType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
        T obj = anaylisType(type, result);
        onSucess(obj);
    }

    private T anaylisType(Type type, String result) {
        try {
            if (type instanceof ParameterizedType) {
                return parseParameterizedType(result, (ParameterizedType) type);
            } else if (type instanceof Class) {
                return parseClass((Class<?>) type, result);
            } else {
                return parseType(result, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private T parseClass(Class<?> rawType, String result) throws Exception {
        if (rawType == null) return null;
        if (rawType == String.class) {
            return (T) result.toString();
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(result);
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(result);
        } else {
            T t = (T) Convert.fromJson(result, rawType);
            return t;
        }
    }

    private T parseParameterizedType(String result, ParameterizedType type) throws Exception {
        if (type == null) return null;

        Type rawType = type.getRawType();                     // 泛型的实际类型
        Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
        if (rawType != LzyResponse.class) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
            return Convert.fromJson(result, type);
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new JsonCallback<LzyResponse<Void>>(this)
                SimpleResponse simpleResponse = Convert.fromJson(result, SimpleResponse.class);
                //noinspection unchecked
                return (T) simpleResponse.toLzyResponse();
            } else {
                // 泛型格式如下： new JsonCallback<LzyResponse<内层JavaBean>>(this)
                LzyResponse lzyResponse = Convert.fromJson(result, type);
                int code = lzyResponse.code;
                //这里的0是以下意思
                //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
                if (code == 1) {
                    //noinspection unchecked
                    return (T) lzyResponse;
                } else if (code == 104) {
                    throw new IllegalStateException("用户授权信息无效");
                } else if (code == 105) {
                    throw new IllegalStateException("用户收取信息已过期");
                } else {
                    Log.e("---->", lzyResponse.msg);
                    //直接将服务端的错误信息抛出，onError中可以获取
                    // throw new IllegalStateException("错误代码：" + code + "，错误信息：" + lzyResponse.msg);
                    throw new IllegalStateException(lzyResponse.msg);
                }
            }
        }
    }

    private T parseType(String result, Type type) throws Exception {
        if (type == null) return null;
        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        return Convert.fromJson(result, type);
    }

    /**
     * 成功回調
     *
     * @param obj
     */
    public abstract void onSucess(T obj);
}
