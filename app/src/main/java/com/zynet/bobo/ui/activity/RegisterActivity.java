package com.zynet.bobo.ui.activity;


import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zynet.bobo.R;
import com.zynet.bobo.base.AbstractMvpBaseActivity;
import com.zynet.bobo.base.BaseContract;

public class RegisterActivity  extends AbstractMvpBaseActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordAgain;
    private Button btnRegister;
    private LinearLayout llLogin;



    @Override
    public int getContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected BaseContract.BasePresenter createPresenter() {
        return null;
    }
}
