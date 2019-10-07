package com.zynet.bobo.mvc.http;

import android.util.Log;

import com.google.gson.Gson;
import com.zynet.bobo.bean.OnResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public abstract class AbstractHttpCallBack<Result> implements ICallBack {
    private static final String TAG = "----------->";

    public abstract void onSucess(Result obj);

    @Override
    public void onSuccess(String result) {
        Log.e("HttpCallBack", "onSuccess: " + result);
        try {
            Result result1 = analysisClassInfo(result);
            onSucess(result1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure() {

    }

    private Result analysisClassInfo(String result) throws Exception {
        Type genType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            Log.e(TAG, "ParameterizedType");
            return parseParameterizedType(result, (ParameterizedType) type);
        } else if (type instanceof Class) {
            Log.e(TAG, "class");
            return parseClass(result, (Class<?>) type);
        } else {
            Log.e(TAG, "other class");
            //   return parseType(response, type);
        }
        throw new RuntimeException();
    }


    private Result parseParameterizedType(String result, ParameterizedType type) {
        // 泛型的实际类型
        Type rawType = type.getRawType();
        // 泛型的参数
        Type typeArgument = type.getActualTypeArguments()[0];
        if (rawType != OnResponse.class) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBan>>(this)
            Result t = Convert.fromJson(result, type);
            return t;
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new JsonCallback<LzyResponse<Void>>(this)
                SimpleResponse simpleResponse = Convert.fromJson(result, SimpleResponse.class);
                //noinspection unchecked
                return (Result) simpleResponse.toLzyResponse();
            } else {
                // 泛型格式如下： new JsonCallback<LzyResponse<内层JavaBean>>(this)
                Gson gson = new Gson();
                OnResponse lzyResponse = gson.fromJson(result, OnResponse.class);
                Log.i(TAG, "数据来了: " + lzyResponse.data.toString());


                int code = lzyResponse.errorCode;
                return (Result) lzyResponse;
                //     Log.e(TAG, "parseParameterizedType: code" + code);
                //这里的0是以下意思
                //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
//            if (code == 1) {
//                //noinspection unchecked
//                return (T) lzyResponse;
//            } else if (code == 104) {
//                throw new IllegalStateException("用户授权信息无效");
//            } else if (code == 105) {
//                throw new IllegalStateException("用户收取信息已过期");
//            } else {
//                Log.e("---->", lzyResponse.msg);
//                //直接将服务端的错误信息抛出，onError中可以获取
//                // throw new IllegalStateException("错误代码：" + code + "，错误信息：" + lzyResponse.msg);
//                throw new IllegalStateException(lzyResponse.msg);
//            }
            }
        }
    }

    private Result parseClass(String result, Class<?> rawType) throws Exception {
        if (rawType == null) {
            return null;
        }
        if (rawType == String.class) {
            //noinspection unchecked
            return (Result) result;
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (Result) new JSONObject(result);
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (Result) new JSONArray(result);
        } else {
            Result t = Convert.fromJson(result, (Type) rawType);
            return t;
        }
    }
}
