package com.zynet.bobo.mvp;

/**
 * @author Bobo
 * @date 2019/9/21
 * describe
 */
public class ApiException extends RuntimeException {

    public String type;

    /**
     * 异常信息
     * @param detailMessage
     */
    public ApiException(String type, String detailMessage) {
        super(detailMessage);
        this.type = type;
    }
}
