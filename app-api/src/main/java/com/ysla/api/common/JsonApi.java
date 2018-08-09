package com.ysla.api.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回实体
 * @author konghang
 */
public class JsonApi {

    @Setter @Getter private int status;

    @Setter @Getter private String message;

    @Setter @Getter private Object data;

    public JsonApi() {
        status = 0;
        message = "请求成功";
    }

    public JsonApi(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonApi(Object data) {
        this();
        this.data = data;
    }

    public JsonApi(int status, String message, Object data) {
        this(status,message);
        this.data = data;
    }

    public JsonApi(ErrorCode code) {
        this(code.getErrorID(),code.getErrorMsg());
    }
}
