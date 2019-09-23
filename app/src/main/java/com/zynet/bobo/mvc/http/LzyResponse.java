package com.zynet.bobo.mvc.http;

import java.io.Serializable;

/**
 * @author Bobo
 * @date 2019/9/23 0023
 * describe
 */
public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int errorCode;
    public String errorMsg;
    public T data;

    @Override
    public String toString() {
        return "onResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
