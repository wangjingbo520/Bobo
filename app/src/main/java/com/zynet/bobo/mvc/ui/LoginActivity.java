package com.zynet.bobo.mvc.ui;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.zynet.bobo.R;
import com.zynet.bobo.mvc.volley.network.NetworkError;
import com.zynet.bobo.mvc.volley.network.RequestHandler;

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

    String url = "https://wanandroid.com/wxarticle/chapters/json";
    @BindView(R.id.tvConent)
    TextView tvConent;

    private static final int RESULT_GET_IP_INFO = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        test();
    }

    private void test() {
        Map<String, String> params = new HashMap<>();
        RequestHandler.addRequestWithDialog(Request.Method.GET, LoginActivity.this, mHandler, RESULT_GET_IP_INFO, null, url, params, null);
    }

    @Override
    public void onHandleMessage(Message msg) {
        switch (msg.what) {
            case RESULT_GET_IP_INFO:
                String result = (String) msg.obj;
                Log.d("demo", result);
                tvConent.setText(result);
//                IpInfo ipInfo = JSON.parseObject(result, IpInfo.class);
//                setIpInfoToView(ipInfo);
                break;
            case NetworkError.NET_ERROR_CUSTOM:
//                mTvCountry.setText("获取请求失败");
//                MessageDialog dialog = new MessageDialog(MainActivity.this);
//                dialog.setMessage("获取请求失败了");
//                dialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(MainActivity.this, HttpsTestActivity.class));
//                    }
//                });
//                dialog.show();
                break;
            default:
                break;
        }
    }


}
