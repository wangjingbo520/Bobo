package com.zynet.bobo.mvp.http;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.zynet.bobo.ui.widget.ProgressDialog;

/**
 * @author Bobo
 * @date 2019/10/14 0014
 * describe
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_DIALOG = 1;

    public static final int DISMISS_DIALOG = 2;

    private boolean mCancelable;

    private ProgressDialogListener mProgressDialogListener;

    private Context mContext;

    private Dialog mDialog;

    public ProgressDialogHandler(boolean mCancelable, ProgressDialogListener
            mProgressDialogListener, Context context) {
        this.mCancelable = mCancelable;
        this.mProgressDialogListener = mProgressDialogListener;
        this.mContext = context;
    }


    /**
     * 用于显示Dialog
     */
    private void initProgressDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(mCancelable);
            if (mCancelable) {
                mDialog.setOnCancelListener(dialogInterface -> mProgressDialogListener.onCancelProgress());
            }
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_DIALOG:
                dismissProgressDialog();
                break;
            default:
                break;
        }
    }
}

