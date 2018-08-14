package com.ysla.web.exception;

import com.alibaba.fastjson.JSON;
import com.ysla.api.model.common.JsonApi;
import com.ysla.api.model.exception.AuthorizeException;
import com.ysla.api.model.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 * @author konghang
 */
@Slf4j
@RestControllerAdvice(value = {"com.ysla.web.controller.user"})
public class GlobalExceptionHandler {

    /**
     * @valid注解校验不通过异常
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonApi validExceptionHandler(BindException e, HttpServletResponse response) {
        List<FieldError> fieldErrors=e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>(4);
        for (FieldError error:fieldErrors){
            map.put(error.getField(),error.getDefaultMessage());
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JsonApi.isFail(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(JSON.toJSONString(map));
    }

    /**
     * 关于用户登录授权同意返回401http状态码
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(AuthorizeException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public JsonApi authorizeExceptionHandler(AuthorizeException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JsonApi.isFail(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage());
    }

    /**
     * 自定义基础常类
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    public JsonApi BaseExceptionHandler(BaseException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        return JsonApi.isFail(HttpStatus.BAD_GATEWAY.value())
                .message(e.getMessage());
    }

    /**
     * 空指针异常
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonApi nullPointerExceptionHandler(NullPointerException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JsonApi.isFail(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonApi maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return JsonApi.isFail(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage());
    }

    /**
     * 输入没有的请求地址
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public JsonApi noHandlerFoundExceptionHandler(NoHandlerFoundException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return JsonApi.isFail(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage());
    }

    /**
     * 捕捉shiro的异常
     * @param e
     * @return
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public JsonApi shiroExceptionHandler(ShiroException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return JsonApi.isFail(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage());
    }

    /**
     * 捕捉UnauthorizedException
     * @param e
     * @return
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public JsonApi unauthorizedExceptionHandler(UnauthorizedException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return JsonApi.isFail(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonApi requestNotReadable(HttpMessageNotReadableException e, HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return JsonApi.isFail(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage());
    }

    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonApi requestTypeMismatch(TypeMismatchException e, HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return JsonApi.isFail(HttpStatus.BAD_REQUEST.value())
                .message("参数类型不匹配,参数" + e.getPropertyName()
                        + "类型应该为" + e.getRequiredType());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonApi requestMissingServletRequest(MissingServletRequestParameterException e, HttpServletResponse response){
        log.error("400..MissingServletRequest");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return JsonApi.isFail(HttpStatus.BAD_REQUEST.value())
                .message("缺少必要参数,参数名称为" + e.getParameterName());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public JsonApi request405(HttpRequestMethodNotSupportedException e, HttpServletResponse response){
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return JsonApi.isFail(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(e.getMessage());
    }

    /**
     * 406错误防止公司打印机问题,不知道别的地方会出现不
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public JsonApi request406(HttpMediaTypeNotAcceptableException e, HttpServletResponse response){
        log.error("打印机相关的出错。。。。。。。");
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        return JsonApi.isFail(HttpStatus.NOT_ACCEPTABLE.value())
                .message(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonApi exceptionHandler(Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("-------------------exception-------------------");
        e.printStackTrace();
        log.error("-------------------exception-------------------");
        return JsonApi.isFail(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage());
    }

}
