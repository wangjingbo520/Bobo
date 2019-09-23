package com.zynet.bobo.mvc.http;

import java.io.Serializable;

/**
 * @author bobo
 * @date 2018/9/1
 * describe
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int errorCode;
    public String errorMsg;

    public LzyResponse toLzyResponse() {
        LzyResponse lzyResponse = new LzyResponse();
        lzyResponse.errorCode = errorCode;
        lzyResponse.errorMsg = errorMsg;
        return lzyResponse;
    }
}