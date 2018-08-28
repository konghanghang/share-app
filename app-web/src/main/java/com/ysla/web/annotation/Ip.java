package com.ysla.web.annotation;

import java.lang.annotation.*;

/**
 * ip注解
 * @author konghang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ip {
}
