package com.zynet.bobo.base;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public interface IBaseView {

    /**
     * 失败
     */
    void showFaild();

    /**
     * 显示当前网络不可用
     */
    void showNoNet();

    /**
     * 重试
     */
    void onRetry();

    /**
     * 没有数据
     */
    void showEmptyView();
}
