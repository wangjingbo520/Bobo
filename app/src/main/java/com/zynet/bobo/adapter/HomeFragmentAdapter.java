package com.zynet.bobo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynet.bobo.R;
import com.zynet.bobo.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bobo
 * @date 2019/11/2 0002
 * describe
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyHolder> {

    private Context mContext;

    private List<TestBean.DataBean> beanList;

    public HomeFragmentAdapter(Context context) {
        beanList = new ArrayList<>();
        mContext = context;
    }

    public void notifyData(List<TestBean.DataBean> beans) {
        if (beanList == null) {
            beanList = new ArrayList<>();
        }
        if (beans == null) {
            return;
        }
        beanList.clear();
        beanList.addAll(beans);
        notifyDataSetChanged();
    }

    public void updateData(List<TestBean.DataBean> newDatas) {
        int size = beanList.size();
        if (newDatas != null) {
            beanList.addAll(newDatas);
            notifyItemRangeInserted(size, newDatas.size());
        }
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recy_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvContent.setText(beanList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        private MyHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

}

