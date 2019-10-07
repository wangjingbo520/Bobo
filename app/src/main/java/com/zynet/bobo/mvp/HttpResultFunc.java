package com.zynet.bobo.mvp;

import android.util.Log;


import io.reactivex.functions.Function;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
    @Override
    public T apply(HttpResult<T> tHttpResult) {
        if (0 != tHttpResult.errorCode) {
            Log.i("------>", "请求发生异常了");
            //非正常返回结构处理
            throw new ApiException(tHttpResult.errorMsg + "", tHttpResult.errorMsg);
        }
        return tHttpResult.data;
    }

}
