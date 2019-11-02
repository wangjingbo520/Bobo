package com.zynet.bobo.adapter;

import android.content.Context;

import com.zynet.bobo.R;
import com.zynet.bobo.bean.TestBean;

/**
 * @author Bobo
 * @date 2019/11/2 0002
 * describe 测试封装的适配器
 */
public class HomeAdapter extends BaseRecyclerAdapter<TestBean.DataBean> {

    public HomeAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, TestBean.DataBean dataBean) {
        setItemText(holder.getView(R.id.tvContent), dataBean.toString());
    }

}
