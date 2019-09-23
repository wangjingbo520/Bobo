package com.zynet.bobo.mvc.http;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface ICallBack {
    /**
     * 成功的回调
     *
     * @param result
     */
    void onSuccess(String result);

    /**
     * 失败的回调
     */
    void onFailure();
}
