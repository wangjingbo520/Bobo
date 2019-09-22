package com.zynet.bobo.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseMvpFragment;
import com.zynet.bobo.base.BaseMvpPresenter;

import butterknife.BindView;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class HomeFragment extends BaseMvpFragment {

    @BindView(R.id.tvContent)
    TextView tvContent;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    protected BaseMvpPresenter createPresenter() {
        return null;
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

    }
}
