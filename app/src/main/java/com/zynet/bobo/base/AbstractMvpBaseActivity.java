package com.zynet.bobo.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.mvp.BaseContract;
import com.zynet.bobo.utils.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public abstract class AbstractMvpBaseActivity<T extends BaseContract.BasePresenter> extends RxAppCompatActivity
        implements BaseContract.BaseView, IBase {
    protected View mRootView;

    protected T mPresenter;

    Unbinder unbinder;

    protected Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        attachView();
        initView(mRootView, savedInstanceState);
        initData();
        dialog = DialogUtils.createLoadingDialog(this, "正在加载");
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


    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    protected abstract T createPresenter();


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

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
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
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
