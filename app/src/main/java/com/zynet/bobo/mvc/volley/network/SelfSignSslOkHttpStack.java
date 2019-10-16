package com.zynet.bobo.mvc.volley.network;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class SelfSignSslOkHttpStack extends HurlStack {
    private com.squareup.okhttp.OkHttpClient okHttpClient;
    private Map<String, SSLSocketFactory> socketFactoryMap;

    public SelfSignSslOkHttpStack(Map<String, SSLSocketFactory> factoryMap) {
        this(new OkHttpClient(), factoryMap);
    }

    public SelfSignSslOkHttpStack(com.squareup.okhttp.OkHttpClient okHttpClient, Map<String, SSLSocketFactory> factoryMap) {
        this.okHttpClient = okHttpClient;
        this.socketFactoryMap = factoryMap;
    }

    /**
     * 里面已经处理掉超时后Volley自动重发请求的问题
     *
     * @param url
     * @return
     * @throws IOException
     */
    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        if ("https".equals(url.getProtocol()) && socketFactoryMap.containsKey(url.getHost())) {
            HttpsURLConnection connection = (HttpsURLConnection) new OkUrlFactory(okHttpClient).open(url);
            connection.setSSLSocketFactory(socketFactoryMap.get(url.getHost()));
            return connection;
        } else {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return connection;
        }
    }
}
