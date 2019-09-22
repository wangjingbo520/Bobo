package com.zynet.bobo.base;

import android.app.Dialog;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public interface IBaseMvpView {
    /**
     * 获取view层的dialog
     *
     * @return retuen
     */
    Dialog getLoadDialog();

    /***
     * 关闭view层的dialog
     */
    void cancelLoadDialog();

}
