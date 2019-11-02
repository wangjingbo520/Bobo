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

/**
 * @author Bobo
 * @date 2019/11/2 0002
 * describe
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyHolder> {
    private TestBean testBean;

    private Context mContext;

    public HomeFragmentAdapter(Context context, TestBean testBean) {
        this.testBean = testBean;
        mContext = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recy_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvContent.setText(testBean.getData().get(position).toString());
    }

    @Override
    public int getItemCount() {
        return testBean.getData().size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        private MyHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

}

