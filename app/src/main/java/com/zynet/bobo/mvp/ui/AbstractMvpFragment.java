package com.zynet.bobo.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zynet.bobo.base.BaseFragment;
import com.zynet.bobo.base.IBaseView;
import com.zynet.bobo.mvp.presenter.BasePresenter;

/**
 * @author Bobo
 * @date 2019/10/21 0021
 * describe
 */
public abstract class AbstractMvpFragment<T1 extends BasePresenter> extends BaseFragment
        implements IBaseView {

    protected T1 mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
    }

    /**
     * 创造presenter
     *
     * @return 返回presenter
     */
    protected abstract T1 createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    @Override
    public void showFaild() {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showContent() {

    }
}
