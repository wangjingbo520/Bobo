package com.zynet.bobo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zynet.bobo.mvc.ui.BaseMvcFragment;

/**
 * @author Bobo
 * @date 2019/10/28 0028
 * describe
 */
public abstract class BaseLazyLoadFragment extends BaseMvcFragment {
    /**
     * // 界面是否已创建完成
     */
    private boolean isViewCreated;
    /**
     * 是否对用户可见
     */
    private boolean isVisibleToUser;
    /**
     * 数据是否已请求
     */
    private boolean isDataLoaded;

    /**
     * 实现具体的数据请求逻辑
     */
    protected abstract void loadData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        tryLoadData();
    }

    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
        }
    }

}
