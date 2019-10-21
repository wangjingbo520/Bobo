package com.zynet.bobo;

import com.zynet.bobo.base.BaseApplication;

/**
 * @author Bobo
 * @date 2019/9/22 
 * describe
 */
public class MyApplication extends BaseApplication {

    public static MyApplication myApplication;

    
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
      
    }

    public static MyApplication getContext() {
        return myApplication;
    }

}
