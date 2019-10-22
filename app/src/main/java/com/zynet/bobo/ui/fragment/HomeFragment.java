package com.zynet.bobo.ui.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zynet.bobo.R;
import com.zynet.bobo.bean.TestBean;
import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.okhttp.CallBackUtil;
import com.zynet.bobo.mvc.http.okhttp.OkhttpUtil;
import com.zynet.bobo.mvp.presenter.BasePresenter;
import com.zynet.bobo.mvp.ui.AbstractMvpFragment;

import butterknife.BindView;
import okhttp3.Call;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe 首页
 */
public class HomeFragment extends AbstractMvpFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void initData() {
        super.initData();
        getData();
    }

    private void getData() {
        String url = MyConfig.BASE_URL + InterfaceMethod.MAIN;
        OkhttpUtil.okHttpGet(mContext, url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                showContent();
                Log.i("--->", response);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(new MyAdapter(new Gson().fromJson(response, TestBean.class)));
            }
        }, true);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private TestBean testBean;

        public MyAdapter(TestBean testBean) {
            this.testBean = testBean;
        }


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
}
