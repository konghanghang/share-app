package com.ysla.api.module.article;

import com.ysla.api.auto.model.ArticleComment;
import com.ysla.api.model.page.PageModel;

/**
 * 文章评论api
 * @author konghang
 */
public interface IArticleCommentService {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(ArticleComment comment);

    /**
     * 根据文章id获取评论
     * @param articleId
     * @param username
     * @param pageModel
     * @return
     */
    PageModel getOneArticleComment(String articleId, String username, PageModel pageModel);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    int delete(String commentId);

    /**
     * 用户收到的评论
     * @param username
     * @param pageModel
     * @return
     */
    PageModel received(String username, PageModel pageModel);

    /**
     * 获取用户的评论历史
     * @param username
     * @param pageModel
     * @return
     */
    PageModel replayTo(String username, PageModel pageModel);

}
