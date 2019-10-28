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
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.okhttputils.JsonGenericsSerializator;
import com.zynet.bobo.mvc.http.okhttputils.OkHttpUtils;
import com.zynet.bobo.mvc.http.okhttputils.callback.GenericsCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe 首页
 */
public class HomeFragment extends BaseLazyLoadFragment {
    RecyclerView recyclerView;
    private String url = MyConfig.BASE_URL + InterfaceMethod.MAIN;

    private void getData() {
        OkHttpUtils.get().url(url).build().execute(new GenericsCallback<TestBean>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                //   mTv.setText("onError:" + e.getMessage());
            }

            @Override
            public void onResponse(TestBean response, int id) {
                dissmissDialog();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new MyAdapter(response));
            }

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                showLoadingDialog();
            }
        });
    }


    @Override
    protected void loadData() {
        getData();
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
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
