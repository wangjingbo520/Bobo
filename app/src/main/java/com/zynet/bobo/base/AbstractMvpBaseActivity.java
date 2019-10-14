package com.zynet.bobo.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.R;
import com.zynet.bobo.mvp.IBaseView;
import com.zynet.bobo.utils.DialogUtils;

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

    protected Dialog mLoadingDialog;

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
        mLoadingDialog = DialogUtils.createLoadingDialog(this, "正在加载");
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
    public void showLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.show();
    }

    protected void showLoadingDialog(String str) {
        if (mLoadingDialog != null) {
            TextView tv = (TextView) mLoadingDialog.findViewById(R.id.tv_load_dialog);
            tv.setText(str);
            mLoadingDialog.show();
        }
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

//    @Override
//    public <T> LifecycleTransformer<T> bindToLife() {
//        return this.bindToLifecycle();
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


}
