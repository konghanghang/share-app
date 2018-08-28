package com.ysla.web.annotation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ysla.api.auto.model.Article;
import com.ysla.api.module.article.IArticleService;
import com.ysla.web.vo.CommentVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 文章注解
 * @author konghang
 */
@Aspect
@Component
public class ArticlesAspect {

    @Reference(version = "${dubbo.service.version}", check = false, timeout = 10000)
    private IArticleService articleService;

    @Pointcut("@annotation(com.ysla.web.annotation.Articles)")
    public void articles() {}

    /**
     * 在评论方法后执行,对文章表评论数+1或者浏览量+1
     */
    @AfterReturning("articles()")
    public void addViewCount(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Articles annotation = targetMethod.getAnnotation(Articles.class);
        String articleId = "";
        Article article = new Article();
        if (annotation.view() > 0){
            // 更新浏览量
            Object[] objects = joinPoint.getArgs();
            articleId = (String) objects[0];
            article.setCountView(1);
        } else if (annotation.comment() > 0){
            // 更新评论量
            Object[] objects = joinPoint.getArgs();
            CommentVO commentVO = (CommentVO) objects[0];
            articleId = commentVO.getArticleId();
            article.setCountComment(1);
        }
        article.setRefArticleId(articleId);
        articleService.updateStatistics(article);
    }

}
