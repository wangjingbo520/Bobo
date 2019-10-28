package com.zynet.bobo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zynet.bobo.ui.widget.dialog.DialogHelper;

import butterknife.ButterKnife;


/**
 * @author Bobo
 * @date 2019/10/19 0019
 * describe
 */
public abstract class BaseFragment extends Fragment {
    public final static String TAG = BaseFragment.class.getSimpleName();
    public Context mContext;
    protected ProgressDialog mLoadingDialog = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), container, false);
        initViews(view);
        ButterKnife.bind(this, view);
        mLoadingDialog = DialogHelper.getLoadingDialog(getActivity());
        return view;
    }


    public void showLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    public void dissmissDialog() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        }
    }

    /**
     * 获取布局
     *
     * @return 布局id
     */
    protected abstract int getContentLayout();

    /**
     * 初始化控件
     *
     * @param view 视图View
     */
    public void initViews(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog = null;
        }
    }
}
