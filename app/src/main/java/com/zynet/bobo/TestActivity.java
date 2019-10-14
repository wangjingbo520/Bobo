package com.zynet.bobo;

import android.widget.TextView;

import com.zynet.bobo.base.AbstractMvpBaseActivity;
import com.zynet.bobo.bean.BannerBean;
import com.zynet.bobo.mvp.presenter.HomePresenter;
import com.zynet.bobo.mvp.view.IHomeView;
import com.zynet.bobo.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe 测试使用的
 */
public class TestActivity extends AbstractMvpBaseActivity<HomePresenter> implements IHomeView {

    private int total = 0;
    private int size = 0;


    @BindView(R.id.tvContent)
    TextView tvContent;

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mPresenter.getData();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this, this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_test;
    }


    @Override
    public void onSuccess(List<BannerBean> bannerBean) {
        if (bannerBean.size() > 0) {
            total = bannerBean.size();
            if (size < total) {
                size++;
                tvContent.setText("总共有" + total +
                        "条数据" + "/n" + "当前是第" + size + "条数据:" + bannerBean.get(size - 1).toString());
            } else {
                ToastUtil.showMessage("数据已经到顶了");
            }
        }
    }
}
