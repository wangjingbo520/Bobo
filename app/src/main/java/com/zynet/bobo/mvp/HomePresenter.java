package com.zynet.bobo.mvp;

import android.util.Log;

import com.zynet.bobo.base.BasePresenter;
import com.zynet.bobo.bean.BannerBean;
import com.zynet.bobo.mvp.http.BaseObserver;
import com.zynet.bobo.utils.ToastUtil;

import java.util.List;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter() {
        super();
    }

    @Override
    public void getData() {
        mView.showLoading();
        mRequestClient.getNewsDetail()
                .compose(mView.bindToLife())
                .subscribe(new BaseObserver<List<BannerBean>>() {
                    @Override
                    public void onSuccess(List<BannerBean> bannerBean) {
                        ToastUtil.showMessage("请求成功");
                        mView.loadData(bannerBean);
                        for (int i = 0; i < bannerBean.size(); i++) {
                            Log.w("--->", "onSuccess: " + bannerBean.get(i).toString());
                        }
                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) {

                    }

                });


    }
}
