package com.zynet.bobo.base;

import android.app.Dialog;
import android.os.Bundle;

import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zynet.bobo.MyApplication;
import com.zynet.bobo.utils.DialogUtils;

import butterknife.ButterKnife;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public abstract class BaseMvpActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends RxAppCompatActivity implements IBaseMvpView, IActivity {

    protected P mPresenter;

    protected Dialog dialog;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        //页面假如没请求，这里有可能返回null,所以要再判空处理
        if (mPresenter != null) {
            mPresenter.attachMvpView((V) this);
        }
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        initView();
        initData(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public Dialog getLoadDialog() {
        return dialog;
    }

    @Override
    public void cancelLoadDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        if (mPresenter != null) {
            mPresenter.detachMvpView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
