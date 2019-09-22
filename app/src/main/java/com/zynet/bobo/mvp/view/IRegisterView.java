package com.zynet.bobo.mvp.view;


import com.zynet.bobo.base.IBaseMvpView;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IRegisterView extends IBaseMvpView {


    /***
     * 注册成功
     */
    void registerSuccess();

    /***
     * 登录失败
     */
    void registerFail();


    /***
     * 获取用户名
     */
    String  getUser();

    /***
     * 获取密码
     * @return
     */
    String  getPassword();

    /***
     * 获取再次输入的密码
     * @return
     */
    String  getPasswordAgain();

}