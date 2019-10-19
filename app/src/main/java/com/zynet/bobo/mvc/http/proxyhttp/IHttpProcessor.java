package com.zynet.bobo.mvc.http.proxyhttp;

import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IHttpProcessor {
    void post(String url, Map<String, Object> params, ICallBack callBack);

    void get(String url,ICallBack callBack);
}
