package com.zynet.bobo.mvp;

import com.zynet.bobo.bean.BannerBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public interface ApiService {

    @GET("wxarticle/chapters/json")
    Observable<HttpResult<List<BannerBean>>> getHomeData();
}
