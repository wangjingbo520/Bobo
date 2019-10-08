package com.zynet.bobo.mvp;


import com.zynet.bobo.base.BaseContract;
import com.zynet.bobo.bean.BannerBean;

import java.util.List;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public interface HomeContract {

    interface View extends BaseContract.BaseView {

        void loadData(List<BannerBean> bannerBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getData();

    }

}
