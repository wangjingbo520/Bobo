package com.zynet.bobo.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseMvpFragment;
import com.zynet.bobo.base.BaseMvpPresenter;
import com.zynet.bobo.base.OnLoadDatasListener;
import com.zynet.bobo.bean.HomeBean;
import com.zynet.bobo.mvp.model.IHomeModel;
import com.zynet.bobo.mvp.presenter.HomeFragmentPresenter;
import com.zynet.bobo.mvp.presenter.RegisterPresenter;
import com.zynet.bobo.mvp.view.IHomeView;
import com.zynet.bobo.mvp.view.IRegisterView;
import com.zynet.bobo.utils.LogUtils;

import butterknife.BindView;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class HomeFragment extends BaseMvpFragment<IHomeView, HomeFragmentPresenter> implements IHomeView {

    @BindView(R.id.tvContent)
    TextView tvContent;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.handleHome();
    }

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
    protected HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter();
    }

    @Override
    public void onSuncess(HomeBean.DataBean bannerBean) {
        LogUtils.e(bannerBean.toString());
    }
}
