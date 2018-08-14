package com.ysla.api.module.article;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.Article;
import com.ysla.api.model.page.PageModel;

import java.util.List;

/**
 * 文章api
 * @author konghang
 */
public interface IArticleService {

    /**
     * 新增文章
     * @param article
     * @return
     */
    Integer insert(Article article);

    /**
     * 获取文章详细
     * @param articleId
     * @param username  用于判断登录用户是否收藏了文章
     * @return
     */
    JSONObject getArticleByRef(String articleId, String username);

    /**
     * 获取所有文章,username不为空则获取某人的所有文章
     * @param username
     * @param pageModel
     * @return
     */
    PageModel articles(String username, PageModel pageModel);

    /**
     * 根据articleId更新文章
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 根据refArticleId更新文章
     * @param article
     * @return
     */
    int updateByRefSelective(Article article);

    /**
     * 精选文章
     * @return
     */
    List<JSONObject> chosen();

}
