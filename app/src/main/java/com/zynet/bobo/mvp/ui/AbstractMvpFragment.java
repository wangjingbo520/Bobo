package com.zynet.bobo.mvp.ui;

import android.view.View;

import com.zynet.bobo.base.BaseFragment;
import com.zynet.bobo.mvp.presenter.BasePresenter;

import butterknife.Unbinder;

/**
 * @author Bobo
 * @date 2019/10/21 0021
 * describe
 */
public class AbstractMvpFragment<T1 extends BasePresenter> extends BaseFragment {

    protected T1 mPresenter;

    protected View mRootView;

    Unbinder unbinder;






}
