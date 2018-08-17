package com.guya2.common.req;

import java.io.Serializable;

/**
 * @author qiyiguo on 2018/7/2.
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appKey;

    private String sign;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
