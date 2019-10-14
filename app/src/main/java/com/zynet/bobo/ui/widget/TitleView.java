package com.zynet.bobo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zynet.bobo.R;


/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class TitleView extends FrameLayout {
    private TextView tvTitle;
    private FrameLayout fl_back;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.base_title, this);
        fl_back = view.findViewById(R.id.fl_back);
        tvTitle = view.findViewById(R.id.tv_title);
        fl_back.setOnClickListener(v -> {

        });
    }


    public void setBackImageGone(boolean b) {
        if (b) {
            fl_back.setVisibility(GONE);
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

}
