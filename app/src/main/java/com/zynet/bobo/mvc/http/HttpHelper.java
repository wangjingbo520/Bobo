package com.zynet.bobo.mvc.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class HttpHelper implements IHttpProcessor {
    private static HttpHelper mHttpHelper;
    private static IHttpProcessor mIHttpProcessor;

    private HttpHelper() {

    }

    public static HttpHelper getInstance() {
        if (mHttpHelper == null) {
            synchronized (HttpHelper.class) {
                if (mHttpHelper == null) {
                    mHttpHelper = new HttpHelper();
                }
            }
        }
        return mHttpHelper;
    }

    public static void init(IHttpProcessor iHttpProcessor) {
        mIHttpProcessor = iHttpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        String finalUrl = appendParams(url, params);
        mIHttpProcessor.post(finalUrl, params, callBack);
    }

    @Override
    public void get(String url) {
        mIHttpProcessor.get(url);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder urlStringBuilder = new StringBuilder(url);
        if (urlStringBuilder.indexOf("?") <= 0) {
            urlStringBuilder.append("?");
        } else {
            if (urlStringBuilder.toString().endsWith("?")) {
                urlStringBuilder.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlStringBuilder.append("&" + entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return urlStringBuilder.toString();
    }

    private String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
