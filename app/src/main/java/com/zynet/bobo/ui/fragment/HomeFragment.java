package com.zynet.bobo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zynet.bobo.R;
import com.zynet.bobo.adapter.HomeFragmentAdapter;
import com.zynet.bobo.base.BaseLazyLoadFragment;
import com.zynet.bobo.bean.TestBean;
import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.okhttputils.JsonGenericsSerializator;
import com.zynet.bobo.mvc.http.okhttputils.OkHttpUtils;
import com.zynet.bobo.mvc.http.okhttputils.callback.GenericsCallback;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Request;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe 首页
 */
public class HomeFragment extends BaseLazyLoadFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int currentPage = 1;

    private HomeFragmentAdapter homeFragmentAdapter;

    @Override
    protected void loadData() {
        getData(InterfaceMethod.MAIN);
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        homeFragmentAdapter = new HomeFragmentAdapter(mContext);
        recyclerView.setAdapter(homeFragmentAdapter);
        refreshLayout.setOnRefreshListener(refresh -> {
            currentPage = 1;
            loadData();
        });
        refreshLayout.setOnLoadMoreListener(refresh -> {
            currentPage++;
            loadData();
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
    }


    public void getData(String url) {
        OkHttpUtils.get().url(MyConfig.BASE_URL + url).build().execute(new GenericsCallback<TestBean>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dissmissDialog();
                Log.e(TAG, "onError: " + e.getMessage());
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onResponse(TestBean response, int id) {
                dissmissDialog();
                if (currentPage == 1) {
                    refreshLayout.finishRefresh();
                    homeFragmentAdapter.notifyData(response.getData());
                } else {
                    refreshLayout.finishLoadMore();
                    homeFragmentAdapter.updateData(response.getData());
                }
            }

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                showLoadingDialog();
            }
        });
    }


}
