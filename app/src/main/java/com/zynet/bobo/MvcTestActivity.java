package com.zynet.bobo;

import android.os.Bundle;
import android.os.Message;

import androidx.recyclerview.widget.RecyclerView;

import com.zynet.bobo.mvc.ui.BaseMvcActivity;
import com.zynet.bobo.constant.InterfaceMethod;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvctest);
        ButterKnife.bind(this);
        httpTest();
    }

    private void httpTest() {
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
