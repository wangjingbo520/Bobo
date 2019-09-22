package com.zynet.bobo.mvp.presenter;

import com.zynet.bobo.base.BaseMvpPresenter;
import com.zynet.bobo.base.OnLoadDatasListener;
import com.zynet.bobo.bean.HomeBean;
import com.zynet.bobo.mvp.model.IHomeModel;
import com.zynet.bobo.mvp.model.impl.HomeModeImpl;
import com.zynet.bobo.mvp.view.IHomeView;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class HomeFragmentPresenter extends BaseMvpPresenter<IHomeView> {

    private IHomeModel iHomeModel;

    public HomeFragmentPresenter() {
        this.iHomeModel = new HomeModeImpl();
    }

    public void handleHome() {
        getView().getLoadDialog().show();
        iHomeModel.getBannerData(new OnLoadDatasListener<HomeBean.DataBean>() {
            @Override
            public void onSuccess(HomeBean.DataBean dataBean) {
                getView().onSuncess(dataBean);
            }

            @Override
            public void onFailure(String error) {
                getView().cancelLoadDialog();
            }
        });
    }

}
