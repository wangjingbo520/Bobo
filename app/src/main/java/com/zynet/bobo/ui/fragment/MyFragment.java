package com.zynet.bobo.ui.fragment;

import android.os.Bundle;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseMvpFragment;
import com.zynet.bobo.base.BaseMvpPresenter;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MyFragment extends BaseMvpFragment {

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }


    @Override
    protected BaseMvpPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
