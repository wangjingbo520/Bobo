package com.zynet.bobo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseLazyLoadFragment;
import com.zynet.bobo.bean.TestBean;
import com.zynet.bobo.constant.InterfaceMethod;

import butterknife.BindView;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe 首页
 */
public class HomeFragment extends BaseLazyLoadFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private void getData() {
        this.<TestBean>getData(InterfaceMethod.MAIN);
    }

    @Override
    protected void loadData() {
        getData();
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected <T> void onSucess(T response) {
        TestBean testBean = (TestBean) response;
        recyclerView.setAdapter(new MyAdapter(testBean));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private TestBean testBean;

        public MyAdapter(TestBean testBean) {
            this.testBean = testBean;
        }


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.recy_item, parent, false);
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
}
