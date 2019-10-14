package com.zynet.bobo.mvp;

import com.zynet.bobo.bean.BannerBean;

import java.util.List;

public interface IHomeView extends IBaseView {
    void onSuccess(List<BannerBean> bannerBean);

}
