package com.zynet.bobo.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe  使用IConFont TextView
 */
public class IconFontTextView extends AppCompatTextView {

    private Context mContext;

    public IconFontTextView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
//        Typeface iconfont = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
//        setTypeface(iconfont);
    }
}
