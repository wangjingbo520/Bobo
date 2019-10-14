package com.zynet.bobo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public abstract class AbstractMvpBaseActivity<T1 extends BasePresenter> extends BaseActivity
        implements IBaseView, IBase {
    protected View mRootView;

    protected T1 mPresenter;

    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        initView(mRootView, savedInstanceState);
        initData();

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(getContentLayout(), container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    protected abstract T1 createPresenter();


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
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


}
