package com.zynet.bobo;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.okhttp.CallBackUtil;
import com.zynet.bobo.mvc.http.okhttp.OkhttpUtil;
import com.zynet.bobo.mvc.ui.BaseMvcActivity;
import com.zynet.bobo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class MvcTestActivity extends BaseMvcActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final int RESULT_GET_IP_INFO = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvctest);
        ButterKnife.bind(this);
        //   volleyHttpTest();
        okHttpTest();
    }

    private void okHttpTest() {
        String url = MyConfig.BASE_URL + InterfaceMethod.MAIN;
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                ToastUtil.showMessage("Success");
                Log.d("kwwl", response);
            }
        }, true);
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


}
