package com.zynet.bobo.mvc.http.okhttputils;

import com.google.gson.Gson;
import com.zynet.bobo.mvc.http.okhttputils.callback.IGenericsSerializator;

/**
 * @author Bobo
 * @date 2019/10/25 0025
 * describe
 */
public class JsonGenericsSerializator implements IGenericsSerializator {
    Gson mGson = new Gson();
    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}
