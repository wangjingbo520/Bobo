package com.zynet.bobo.ui.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;


public class DialogHelper {

    public static ProgressDialog getLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在加载中...");
        //    ProgressDialog dialog = new Dialog(context, R.style.default_dialog);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.loading_dialog, null);
//        dialog.setContentView(view);
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER);
//        window.setDimAmount(0.1f);
        return progressDialog;
    }
}
