package com.zynet.bobo;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynet.bobo.bean.TestBean;
import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.ui.BaseMvcActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MvcTestActivity extends BaseMvcActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final int RESULT_GET_IP_INFO = 101;
    @BindView(R.id.SimpleMultiStateView)
    com.zynet.bobo.ui.widget.statusview.SimpleMultiStateView SimpleMultiStateView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvctest);
        ButterKnife.bind(this);
        initStateView(SimpleMultiStateView);
        //   volleyHttpTest();
        okHttpTest();
    }

    private void okHttpTest() {
        String url = MyConfig.BASE_URL + InterfaceMethod.MAIN;
//        OkhttpUtil.okHttpGet(this, url, new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
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

    private void volleyHttpTest() {
        addGetRequest(InterfaceMethod.MAIN, RESULT_GET_IP_INFO);
    }

    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case RESULT_GET_IP_INFO:
                String result = (String) msg.obj;
                break;
            default:
                break;
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private TestBean testBean;

        public MyAdapter(TestBean testBean) {
            this.testBean = testBean;
        }


        @Override
        public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
