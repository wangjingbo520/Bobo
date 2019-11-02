package com.zynet.bobo.adapter;

/**
 * @author Bobo
 * @date 2019/11/2 0002
 * describe
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe RecyclerAdapter简单封装
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    /**
     * //数据集合
     */
    private List<T> mDates;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * //单击事件
     */
    protected OnItemClickListner onItemClickListner;

//    public BaseRecyclerAdapter(List<T> mDates) {
//        this.mDates = mDates;
//        setHasStableIds(true);
//    }

    private int mLayoutId;

    public BaseRecyclerAdapter(Context mContext, int layoutId) {
        //  this.mContext = mContext;
        mDates = new ArrayList<>();
        this.mLayoutId = layoutId;
        setHasStableIds(true);
        setHasStableIds(true);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        final BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        view.setOnClickListener(v -> {
            if (onItemClickListner != null) {
                onItemClickListner.onItemClickListner(v, baseViewHolder.getLayoutPosition());
            }

        });
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseViewHolder holder, final int position) {
        T t = mDates.get(position);
        bindData(holder, position, t);
    }

    @Override
    public int getItemCount() {
        return mDates == null ? 0 : mDates.size();
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    public void refresh(List<T> datas) {
        this.mDates.clear();
        this.mDates.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param newDatas
     */
    public void addData(List<T> newDatas) {
        int size = mDates.size();
        if (newDatas != null) {
            mDates.addAll(newDatas);
            notifyItemRangeInserted(size, newDatas.size());
        }
    }

    public List<T> getData() {
        if (mContext != null) {
            return mDates;
        }
        return new ArrayList<>();
    }

//    /**
//     * 添加数据(局部刷新，局部刷新时必须充血getItemId方法，同时setHasStableIds(true))
//     *
//     * @param datas
//     */
//    public void addDataWithoutAnim(List<T> datas) {
//        if (datas == null)
//            return;
//        int size = mDates.size();
//        this.mDates.addAll(datas);
//        notifyItemRangeChanged(size, datas.size());
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     * @param t
     */
    protected abstract void bindData(BaseViewHolder holder, int position, T t);

    /**
     * 设置文本属性
     *
     * @param view
     * @param text
     */
    public void setItemText(View view, String text) {
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    /**
     * 设置图片属性
     *
     * @param view
     * @param url
     */
    public void setItemImage(View view, String url) {
        if (view instanceof ImageView && mContext != null) {
            ImageView imageView = (ImageView) view;
            //  new GlideImageLoader().displayImage(mContext, url, imageView);
        }
    }

    /**
     * 设置圆形图片属性
     *
     * @param view
     * @param url
     */
    public void setItemImageCircle(View view, String url) {
        if (view instanceof ImageView && mContext != null) {
            ImageView imageView = (ImageView) view;
            //     new GlideImageLoader().displayImageForCircle(mContext, url, imageView);
        }
    }

    /**
     * 设置圆形图片属性
     *
     * @param view
     * @param drawable
     */
    public void setItemImageDrawable(View view, Drawable drawable) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageDrawable(drawable);
        }
    }

    /**
     * 设置控件可见性
     *
     * @param view
     * @param visible
     */
    public void setItemViewVisible(View view, int visible) {
        if (view != null) {
            switch (visible) {
                case View.VISIBLE:
                    view.setVisibility(View.VISIBLE);
                    break;
                case View.INVISIBLE:
                    view.setVisibility(View.INVISIBLE);
                    break;
                case View.GONE:
                    view.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViewSparseArray;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViewSparseArray = new SparseArray<>();
        }

        /**
         * 获取设置的view
         *
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewSparseArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewSparseArray.put(id, view);
            }
            return view;
        }
    }
}
