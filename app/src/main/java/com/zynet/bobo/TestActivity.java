package com.zynet.bobo;

import com.zynet.bobo.base.AbstractMvpBaseActivity;
import com.zynet.bobo.mvp.HomePresenter;

import butterknife.OnClick;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe 测试使用的
 */
public class TestActivity extends AbstractMvpBaseActivity<HomePresenter> {

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mPresenter.getData();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_test;
    }

}
