package com.zynet.bobo.mvp;

import com.zynet.bobo.bean.BannerBean;
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
        mRequestClient.getNewsDetail()
                .compose(RxSchedulers.applySchedulers())
                .map(new HttpResultFunc<>())
                //   .compose(mView.bindToLife())
                .subscribe(new BaseObserver<List<BannerBean>>() {
                    @Override
                    public void onSuccess(List<BannerBean> bannerBean) {
                        ToastUtil.showMessage("请求成功");
                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) {

                    }

                });


    }
}
