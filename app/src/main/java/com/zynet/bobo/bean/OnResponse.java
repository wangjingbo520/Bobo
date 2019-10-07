package com.zynet.bobo.bean;

import java.io.Serializable;

/**
 * @author Bobo
 * @date 2019/10/2 0002
 * describe
 */
public class OnResponse<T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;

    public int errorCode;
    public String errorMsg;
    public T data;

    @Override
    public String toString() {
        return "OnResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
