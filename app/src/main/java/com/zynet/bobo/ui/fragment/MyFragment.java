package com.zynet.bobo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.zynet.bobo.R;
import com.zynet.bobo.mvp.presenter.BasePresenter;
import com.zynet.bobo.mvp.ui.AbstractMvpFragment;
import com.zynet.bobo.ui.activity.LoginActivity;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MyFragment extends AbstractMvpFragment {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_my;
    }
}
