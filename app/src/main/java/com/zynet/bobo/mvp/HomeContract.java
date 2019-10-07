package com.zynet.bobo.mvp;


import com.zynet.bobo.bean.BannerBean;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public interface HomeContract {

    interface View extends BaseContract.BaseView {

        void loadData(BannerBean articleBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getData();

    }

}
