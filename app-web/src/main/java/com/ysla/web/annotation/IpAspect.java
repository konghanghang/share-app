package com.ysla.web.annotation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ysla.api.auto.model.IpInfo;
import com.ysla.api.module.data.IpInfoService;
import com.ysla.api.utils.http.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 统计ip信息
 * @author konghang
 */
@Aspect
@Component
public class IpAspect {

    private final String localIp = "0:0:0:0:0:0:0:1";

    @Reference(version = "${dubbo.service.version}", check = false, timeout = 10000)
    private IpInfoService ipInfoService;

    @Pointcut("@annotation(com.ysla.web.annotation.Ip)")
    public void ip() {}


    @Before("ip()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        String ip = IpUtils.getRealIp(request);
        if (!localIp.equals(ip)) {
            IpInfo info = ipInfoService.getIpLocation(ip);
        }
    }

}
