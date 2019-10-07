package com.zynet.bobo.mvp;

/**
 * @author Bobo
 * @date 2019/10/7 0007
 * describe
 */
public class HttpResult<T> {

    public int errorCode;
    public String errorMsg;
    public T data;

    @Override
    public String toString() {
        return "HttpResult{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }


}
