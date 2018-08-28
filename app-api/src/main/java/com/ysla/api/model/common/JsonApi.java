package com.ysla.api.model.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回实体
 * @author konghang
 */
public class JsonApi<T> {

    private static final int OK = 0;
    private static final int FAIL = 1;

    @Setter@Getter private int code = OK;
    @Setter@Getter private T data;
    @Setter@Getter private String message = "请求成功！";

    public JsonApi() {}

    public static JsonApi isOk(){
        return new JsonApi();
    }

    public static JsonApi isFail(){
        return isFail(FAIL);
    }

    public static JsonApi isFail(Integer code){
        return new JsonApi().code(code);
    }

    public static JsonApi isFail(Throwable e){
        return isFail().message(e);
    }

    public static JsonApi isFail(ErrorCode errorCode){
        return new JsonApi()
                .code(errorCode.getErrorID())
                .message(errorCode.getErrorMsg());
    }

    public JsonApi code(int code){
        this.setCode(code);
        return this;
    }

    public JsonApi message(Throwable e){
        this.setMessage(e.getMessage());
        return this;
    }

    public JsonApi message(String message){
        this.setMessage(message);
        return this;
    }

    public JsonApi data(T data){
        this.setData(data);
        return this;
    }
}
