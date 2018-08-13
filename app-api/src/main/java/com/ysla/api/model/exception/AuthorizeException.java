package com.ysla.api.model.exception;

import com.ysla.api.model.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户认证异常类
 * @author konghang
 */
public class AuthorizeException extends RuntimeException {

    @Getter @Setter private ErrorCode errorCode;

    public AuthorizeException(){
        super(ErrorCode.CORE_SERVICE_FAIL.getErrorMsg());
        this.errorCode = ErrorCode.CORE_SERVICE_FAIL;
    }

    public AuthorizeException(ErrorCode errorCode){
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

}
