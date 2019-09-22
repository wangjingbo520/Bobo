package com.zynet.bobo.mvp.presenter;

import android.text.TextUtils;

import com.zynet.bobo.base.BaseMvpPresenter;
import com.zynet.bobo.bean.CurrencyBean;
import com.zynet.bobo.base.OnLoadDatasListener;
import com.zynet.bobo.mvp.model.IRegisterModel;
import com.zynet.bobo.mvp.model.impl.RegisterModelImpl;
import com.zynet.bobo.mvp.view.IRegisterView;
import com.zynet.bobo.utils.ToastUtil;


/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class RegisterPresenter extends BaseMvpPresenter<IRegisterView> {
    private IRegisterModel iRegisterModel;

    public RegisterPresenter() {
        this.iRegisterModel = new RegisterModelImpl();
    }

    public void handleRegister() {
        if (mView == null) {
            return;
        }
        if (TextUtils.isEmpty(mView.getUser()) || TextUtils.isEmpty(mView.getPassword())) {
            ToastUtil.showMessage("用户名或密码不能为空");
            return;
        }

        getView().getLoadDialog().show();
        iRegisterModel.handleRegister(mView.getUser(), mView.getPassword(), mView.getPasswordAgain(), new OnLoadDatasListener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                getView().cancelLoadDialog();
                getView().registerSuccess();

            }

            @Override
            public void onFailure(String error) {
                getView().cancelLoadDialog();
                getView().registerFail();
            }
        });
    }


}
