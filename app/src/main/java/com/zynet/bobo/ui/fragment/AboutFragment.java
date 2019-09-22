package com.zynet.bobo.ui.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseMvpFragment;
import com.zynet.bobo.base.BaseMvpPresenter;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class AboutFragment extends BaseMvpFragment {

    public AboutFragment() {

    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }


    @Override
    protected BaseMvpPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_abou;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public Dialog getLoadDialog() {
        return null;
    }

    @Override
    public void cancelLoadDialog() {

    }
}
