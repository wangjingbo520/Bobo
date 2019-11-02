package com.zynet.bobo.adapter;

import android.support.annotation.NonNull;

import com.zynet.bobo.R;
import com.zynet.bobo.adapter.base.BaseRecyclerViewAdapter;
import com.zynet.bobo.adapter.base.BaseViewHolder;
import com.zynet.bobo.bean.TestBean;

/**
 * @author Bobo
 * @date 2019/11/2 0002
 * describe 测试封装的适配器
 */
public class HomeAdapter extends BaseRecyclerViewAdapter<TestBean.DataBean, BaseViewHolder> {

    public HomeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TestBean.DataBean item) {
        helper.setText(R.id.tvContent, item.toString());
        helper.addOnClickListener(R.id.btnDelete);

    }
}
