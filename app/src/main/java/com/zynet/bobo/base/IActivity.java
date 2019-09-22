package com.zynet.bobo.base;

import android.os.Bundle;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IActivity {

    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);
}
