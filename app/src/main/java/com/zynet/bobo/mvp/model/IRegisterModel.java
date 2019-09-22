package com.zynet.bobo.mvp.model;

import com.zynet.bobo.bean.CurrencyBean;
import com.zynet.bobo.base.OnLoadDatasListener;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IRegisterModel {
    void handleRegister(String username, String password, String passwordagain , OnLoadDatasListener<CurrencyBean.DataBean> onLoadDatasListener);
}
