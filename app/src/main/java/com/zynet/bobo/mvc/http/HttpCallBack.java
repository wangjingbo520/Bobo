package com.zynet.bobo.mvc.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;


/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public abstract class HttpCallBack<T> implements ICallBack {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> aClass = analysisClassInfo(this);
        T obj = (T) gson.fromJson(result, aClass);
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


//    private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
//        if (type == null) return null;
//        ResponseBody body = response.body();
//        if (body == null) return null;
//        JsonReader jsonReader = new JsonReader(body.charStream());
//       // 泛型的实际类型
//        Type rawType = type.getRawType();
//        // 泛型的参数
//        Type typeArgument = type.getActualTypeArguments()[0];
//        if (rawType != LzyResponse.class) {
//            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
//            T t = Convert.fromJson(jsonReader, type);
//            response.close();
//            return t;
//        } else {
//            if (typeArgument == Void.class) {
//                // 泛型格式如下： new JsonCallback<LzyResponse<Void>>(this)
//                SimpleResponse simpleResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
//                response.close();
//                //noinspection unchecked
//                return (T) simpleResponse.toLzyResponse();
//            } else {
//                // 泛型格式如下： new JsonCallback<LzyResponse<内层JavaBean>>(this)
//                LzyResponse lzyResponse = Convert.fromJson(jsonReader, type);
//                response.close();
//                int code = lzyResponse.code;
//                //这里的0是以下意思
//                //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
//                if (code == 1) {
//                    //noinspection unchecked
//                    return (T) lzyResponse;
//                } else if (code == 104) {
//                    throw new IllegalStateException("用户授权信息无效");
//                } else if (code == 105) {
//                    throw new IllegalStateException("用户收取信息已过期");
//                } else {
//                    Log.e("---->", lzyResponse.msg);
//                    //直接将服务端的错误信息抛出，onError中可以获取
//                    // throw new IllegalStateException("错误代码：" + code + "，错误信息：" + lzyResponse.msg);
//                    throw new IllegalStateException(lzyResponse.msg);
//                }
//            }
//        }
//    }

    /**
     * 成功回調
     *
     * @param obj
     */
    public abstract void onSucess(T obj);
}
