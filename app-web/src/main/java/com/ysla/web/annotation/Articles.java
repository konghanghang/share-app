package com.ysla.web.annotation;

import java.lang.annotation.*;

/**
 * 文章统计
 * @author konghang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Articles {

    /**
     * 文章浏览+1
     * @return
     */
    int view() default 0;

    /**
     * 文章评论+1
     * @return
     */
    int comment() default 0;

}
