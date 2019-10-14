package com.zynet.bobo.mvp.view;

import com.zynet.bobo.base.IBaseView;
import com.zynet.bobo.bean.BannerBean;

import java.util.List;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public interface IHomeView extends IBaseView {

    /**
     * 请求成功
     *
     * @param bannerBean 数据
     */
    void onSuccess(List<BannerBean> bannerBean);

}
