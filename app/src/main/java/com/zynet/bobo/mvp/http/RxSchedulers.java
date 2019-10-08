package com.zynet.bobo.mvp.http;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

