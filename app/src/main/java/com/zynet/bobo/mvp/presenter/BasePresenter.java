package com.zynet.bobo.mvp.presenter;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.base.IBaseView;
import com.zynet.bobo.mvp.http.RequestClient;

/**
 * @author Bobo
 * @date 2019/10/7
 * describe
 */
public class BasePresenter<T extends IBaseView> {
    /**
     * 网络请求
     */
    protected RequestClient mRequestClient;

    protected RxAppCompatActivity mContext;

    protected T mView;

    public BasePresenter(RxAppCompatActivity rxAppCompatActivity, IBaseView iBaseView) {
        mRequestClient = RequestClient.getInstance();
        this.mContext = rxAppCompatActivity;
        this.mView = (T) iBaseView;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
