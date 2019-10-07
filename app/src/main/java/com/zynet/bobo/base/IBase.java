package com.zynet.bobo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public interface IBase {
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getView();

    int getContentLayout();

    void initView(View view, Bundle savedInstanceState);

    void initData();
}
