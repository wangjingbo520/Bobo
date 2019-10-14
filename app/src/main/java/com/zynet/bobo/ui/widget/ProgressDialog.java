package com.zynet.bobo.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zynet.bobo.R;

/**
 * @author Bobo
 * @date 2019/10/14 0014
 * describe
 */
public class ProgressDialog extends AlertDialog {
    private LayoutInflater inflater;
    private TextView tvDes;

    public ProgressDialog(Context context) {
        super(context, R.style.dialog);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.loading_dialog, null);
        tvDes = view.findViewById(R.id.tv_load_dialog);
        setContentView(view);
        setCanceledOnTouchOutside(false);
    }

    public void setDes(String des) {
        tvDes.setText("" + des);
    }
}


