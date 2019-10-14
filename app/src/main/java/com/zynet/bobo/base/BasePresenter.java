package com.zynet.bobo.base;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.mvp.IBaseView;
import com.zynet.bobo.mvp.http.RequestClient;

/**
 * @author Bobo
 * @date 2019/10/7 0007
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

    protected void attachView(T view) {
        this.mView = view;
    }

    protected void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
