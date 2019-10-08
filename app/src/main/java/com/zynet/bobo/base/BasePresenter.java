package com.zynet.bobo.base;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.mvp.http.RequestClient;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    /**
     * 网络请求
     */
    protected RequestClient mRequestClient;

    public BasePresenter() {
        mRequestClient = RequestClient.getInstance();
    }

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
