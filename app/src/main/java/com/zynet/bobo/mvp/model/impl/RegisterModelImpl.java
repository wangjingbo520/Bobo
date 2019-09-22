package com.zynet.bobo.mvp.model.impl;

import com.zynet.bobo.bean.CurrencyBean;
import com.zynet.bobo.http.BaseObserver;
import com.zynet.bobo.http.RetrofitFactory;
import com.zynet.bobo.mvp.model.IRegisterModel;
import com.zynet.bobo.base.OnLoadDatasListener;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class RegisterModelImpl implements IRegisterModel {


    @Override
    public void handleRegister(String username, String password, String passwordagain, final OnLoadDatasListener<CurrencyBean.DataBean> onLoadDatasListener) {

        RetrofitFactory
                .getInstence()
                .register(username, password, passwordagain, new BaseObserver<CurrencyBean.DataBean>() {
                    @Override
                    protected void onSuccees(CurrencyBean.DataBean dataBean) throws Exception {
                        onLoadDatasListener.onSuccess(dataBean);

                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                        onLoadDatasListener.onFailure(error);
                    }
                });

    }
}