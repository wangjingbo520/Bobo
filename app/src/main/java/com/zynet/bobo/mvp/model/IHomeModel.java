package com.zynet.bobo.mvp.model;

import com.zynet.bobo.base.OnLoadDatasListener;
import com.zynet.bobo.bean.HomeBean;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IHomeModel {
    void getBannerData(OnLoadDatasListener<HomeBean.DataBean> onLoadDatasListener);
}
