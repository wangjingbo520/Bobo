package com.zynet.bobo.ui.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseLazyLoadFragment;

import butterknife.BindView;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class AboutFragment extends BaseLazyLoadFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public AboutFragment() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_about;
    }


    @Override
    protected void loadData() {
        initData();
    }

    private void initData() {
        String[] imags = mContext.getResources().getStringArray(R.array.imags);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setAdapter(new MyAdapter(imags));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        private String[] imags;

        public MyAdapter(String[] imags) {
            this.imags = imags;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(mContext);
            return new MyHolder(imageView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            Glide.with(mContext).load(imags[position]).into(holder.view);
        }

        @Override
        public int getItemCount() {
            return imags.length;
        }


        class MyHolder extends RecyclerView.ViewHolder {

            ImageView view;

            private MyHolder(View itemView) {
                super(itemView);
                view = (ImageView) itemView;
            }
        }

    }
}
