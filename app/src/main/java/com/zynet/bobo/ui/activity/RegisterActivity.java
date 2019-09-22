package com.zynet.bobo.ui.activity;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseMvpActivity;
import com.zynet.bobo.mvp.presenter.RegisterPresenter;
import com.zynet.bobo.mvp.view.IRegisterView;
import com.zynet.bobo.utils.SpUtils;

public class RegisterActivity  extends BaseMvpActivity<IRegisterView, RegisterPresenter> implements IRegisterView {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordAgain;
    private Button btnRegister;
    private LinearLayout llLogin;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }


    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public Dialog getLoadDialog() {
        return dialog;
    }

    @Override
    public void cancelLoadDialog() {
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void registerSuccess() {
        SpUtils.SetConfigString("username",etUsername.getText().toString());
       // LoginActivity.startMain(this);
    }

    @Override
    public void registerFail() {

    }

    @Override
    public String getUser() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getPasswordAgain() {
        return etPasswordAgain.getText().toString().trim();
    }
}
