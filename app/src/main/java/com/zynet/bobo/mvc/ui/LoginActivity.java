package com.zynet.bobo.mvc.ui;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.android.volley.Request;
import com.zynet.bobo.R;
import com.zynet.bobo.constant.Config;
import com.zynet.bobo.constant.InterfaceMethod;
import com.zynet.bobo.mvc.volley.network.RequestHandler;
import com.zynet.bobo.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class LoginActivity extends BaseMvcActivity {

    @BindView(R.id.tvConent)
    TextView tvContent;

    private static final int RESULT_GET_IP_INFO = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        test();
    }

    private void test() {
        Map<String, String> params = new HashMap<>(16);
        RequestHandler.addRequestWithDialog(Request.Method.GET, this, mHandler, RESULT_GET_IP_INFO, null, Config.BASE_URL + InterfaceMethod.MAIN, params, null);
    }

    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case RESULT_GET_IP_INFO:
                String result = (String) msg.obj;
                LogUtil.d(result);
                tvContent.setText(result);
//                IpInfo ipInfo = JSON.parseObject(result, IpInfo.class);
//                setIpInfoToView(ipInfo);
                break;
            default:
                break;
        }
    }


}
