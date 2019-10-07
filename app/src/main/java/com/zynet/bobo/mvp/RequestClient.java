package com.zynet.bobo.mvp;

import com.zynet.bobo.MyApplication;
import com.zynet.bobo.bean.BannerBean;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class RequestClient {

    private static RequestClient requestClient;

    private ApiService apiService;

    public static RequestClient getInstance() {
        if (null == requestClient) {
            requestClient = new RequestClient();
        }
        return requestClient;
    }


    String BASE_URL = "https://www.wanandroid.com/";
    private RequestClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        builder.cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = mRetrofit.create(ApiService.class);
    }


    public Observable<HttpResult<List<BannerBean>>> getNewsDetail() {
        return apiService.getHomeData();
    }


}
