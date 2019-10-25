package com.zynet.bobo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynet.bobo.R;
import com.zynet.bobo.base.BaseLazyFragment;
import com.zynet.bobo.bean.TestBean;
import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.okhttputils.JsonGenericsSerializator;
import com.zynet.bobo.mvc.http.okhttputils.OkHttpUtils;
import com.zynet.bobo.mvc.http.okhttputils.callback.GenericsCallback;
import com.zynet.bobo.mvp.presenter.BasePresenter;

import butterknife.BindView;
import okhttp3.Call;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe 首页
 */
public class HomeFragment extends BaseLazyFragment {
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


    private void getData() {
        String url = MyConfig.BASE_URL + InterfaceMethod.MAIN;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new GenericsCallback<TestBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //   mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(TestBean response, int id) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        recyclerView.setAdapter(new MyAdapter(response));
                        // mTv.setText("onResponse:" + response.username);
                    }
                });
//        OkhttpUtil.okHttpGet(mContext, url, new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                showFaild();
//            }
//
//            @Override
//            public void onResponse(String response) {
//                showContent();
//                Log.i("--->", response);
//                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//                recyclerView.setAdapter(new MyAdapter(new Gson().fromJson(response, TestBean.class)));
//            }
//        }, false);
    }


    @Override
    public void onLazyLoad() {
        getData();

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
