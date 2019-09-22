package com.zynet.bobo.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.zynet.bobo.MyApplication;
import com.zynet.bobo.utils.DialogUtils;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zynet.bobo.utils.HideUtil;

import butterknife.ButterKnife;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxFragment
        implements IBaseMvpView, IFragment {

    protected P mPresenter;
    protected View rootView;
    protected Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        if (rootView instanceof ViewGroup) {
            //初始化触摸关闭软键盘
            HideUtil.init(getActivity(), (ViewGroup) rootView);
        }

        ButterKnife.bind(this, rootView);

        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        //页面假如没请求，这里有可能返回null,所以要再判空处理
        if (mPresenter!=null){
            mPresenter.attachMvpView((V) this);
        }
        dialog = DialogUtils.createLoadingDialog(getActivity(), "请稍后...");

        //  initImmersionBar();
        initView();
        initData(savedInstanceState);
        return rootView;
    }


    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
//    protected void initImmersionBar() {
//        //设置共同沉浸式样式
//        ImmersionBar.with(this).init();
//    }

}
