package com.zynet.bobo.mvc.ui;

import com.zynet.bobo.base.BaseFragment;
import com.zynet.bobo.constant.MyConfig;
import com.zynet.bobo.mvc.http.okhttputils.JsonGenericsSerializator;
import com.zynet.bobo.mvc.http.okhttputils.OkHttpUtils;
import com.zynet.bobo.mvc.http.okhttputils.callback.GenericsCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author Bobo
 * @date 2019/10/28 0028
 * describe
 */
public abstract class BaseMvcFragment extends BaseFragment {

    public <T> void getData(String url) {
        OkHttpUtils.get().url(MyConfig.BASE_URL + url).build().execute(new GenericsCallback<T>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dissmissDialog();
            }

            @Override
            public void onResponse(T response, int id) {
                dissmissDialog();
                onSucess(response);
            }

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                showLoadingDialog();
            }
        });
    }

    protected abstract <T> void onSucess(T response);


}
