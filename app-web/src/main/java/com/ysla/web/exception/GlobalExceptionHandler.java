package com.ysla.web.exception;

import com.ysla.api.model.common.JsonApi;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 处理在进入Controller之前的错误，譬如请求一个不存在的地址，404错误
 * https://blog.csdn.net/tianyaleixiaowu/article/details/70145251
 * https://www.jianshu.com/p/3998ea8b53a8
 * @see org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
 * @author konghang
 */
@Controller
@RequestMapping("${server.error.path:/error}")
public class GlobalExceptionHandler implements ErrorController {

    /**
     * 错误信息的构建工具.
     */
    @Resource
    private ErrorInfoBuilder errorInfoBuilder;

    /**
     * 错误信息页的路径
     */
    private final static String DEFAULT_ERROR_VIEW = "error";

    /**
     * 获取错误控制器的映射路径.
     */
    @Override
    public String getErrorPath() {
        return errorInfoBuilder.getErrorProperties().getPath();
    }

    /**
     * 情况1：若预期返回类型为text/html,s则返回错误信息页(View).
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ModelAndView errorHtml(HttpServletRequest request) {
        return new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo", errorInfoBuilder.getErrorInfo(request));
    }

    /**
     * 情况2：其它预期类型 则返回详细的错误信息(JSON).
     */
    @RequestMapping
    @ResponseBody
    public JsonApi error(HttpServletRequest request) {
        return errorInfoBuilder.getErrorInfo(request);
    }

}