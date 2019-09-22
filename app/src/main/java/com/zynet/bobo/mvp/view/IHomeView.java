package com.zynet.bobo.mvp.view;

import com.zynet.bobo.base.IBaseMvpView;
import com.zynet.bobo.bean.HomeBean;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IHomeView extends IBaseMvpView {

    void onSuncess(HomeBean.DataBean bannerBean);
}
