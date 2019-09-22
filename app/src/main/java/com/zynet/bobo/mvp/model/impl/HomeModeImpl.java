package com.zynet.bobo.mvp.model.impl;

import com.zynet.bobo.base.OnLoadDatasListener;
import com.zynet.bobo.bean.HomeBean;
import com.zynet.bobo.mvp.http.BaseObserver;
import com.zynet.bobo.mvp.http.RetrofitFactory;
import com.zynet.bobo.mvp.model.IHomeModel;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class HomeModeImpl implements IHomeModel {
    @Override
    public void getBannerData(OnLoadDatasListener<HomeBean.DataBean> onLoadDatasListener) {
        RetrofitFactory
                .getInstence()
                .getHomeData(new BaseObserver<HomeBean.DataBean>() {
                    @Override
                    protected void onSuccees(HomeBean.DataBean dataBean) throws Exception {
                        onLoadDatasListener.onSuccess(dataBean);
                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                        onLoadDatasListener.onFailure(error);
                    }
                });

    }
}
