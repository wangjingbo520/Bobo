package com.zynet.bobo.mvp.http;

/**
 * @author Bobo
 * @date 2019/9/22 0022
 * describe
 */
public class ApiException extends RuntimeException {

    private String errorCode;

    public ApiException(String code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
