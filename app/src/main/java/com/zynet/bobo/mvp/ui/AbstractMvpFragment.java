package com.zynet.bobo.mvp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.zynet.bobo.base.BaseFragment;
import com.zynet.bobo.base.IBase;
import com.zynet.bobo.base.IBaseView;
import com.zynet.bobo.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Bobo
 * @date 2019/10/21 0021
 * describe
 */
public abstract class AbstractMvpFragment<T1 extends BasePresenter> extends BaseFragment
        implements IBaseView, IBase {

    protected T1 mPresenter;

    protected View mRootView;

    private Unbinder mUnBinder;


    public FragmentActivity mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if (getLayoutId() != 0) {
            this.mContext = this.getActivity();
            view = inflater.inflate(getContentLayout(), container, false);
            mUnBinder = ButterKnife.bind(this, view);
            mRootView = view;
            initView(view, savedInstanceState);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public View getRootView() {
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public int getContentLayout() {
        return getLayoutId();
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    /**
     * 初始化布局
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 创造presenter
     *
     * @return 返回presenter
     */
    protected abstract T1 createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

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
