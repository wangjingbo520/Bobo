package com.zynet.bobo.ui.fragment;

import com.zynet.bobo.R;
import com.zynet.bobo.mvp.presenter.BasePresenter;
import com.zynet.bobo.mvp.ui.AbstractMvpFragment;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MyFragment extends AbstractMvpFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
