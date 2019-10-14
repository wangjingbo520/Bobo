package com.zynet.bobo.mvp.presenter;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.base.BasePresenter;
import com.zynet.bobo.bean.BannerBean;
import com.zynet.bobo.mvp.view.IHomeView;
import com.zynet.bobo.mvp.http.ProgressSubscriber;

import java.util.List;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class HomePresenter extends BasePresenter<IHomeView> {

    public HomePresenter(RxAppCompatActivity activity, IHomeView iBaseView) {
        super(activity, iBaseView);
    }

    public void getData() {
        mRequestClient.getNewsDetail()
                .compose(mContext.bindToLifecycle())
                .subscribe(new ProgressSubscriber<List<BannerBean>>(mContext) {
                    @Override
                    public void onNext(List<BannerBean> bannerBeans) {
                        if (bannerBeans != null) {
                            mView.onSuccess(bannerBeans);
                        }
                    }
                });
    }
}
