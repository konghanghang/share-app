package com.ysla.provider.module.article.dao;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.mapper.ArticleCommentMapper;
import com.ysla.api.auto.model.ArticleComment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 文章评论mapper扩展
 * @author konghang
 */
@Mapper
public interface IArticleCommentDao extends ArticleCommentMapper {

    /**
     * 获取一级评论个数
     * @param articleId
     * @return
     */
    @Select({"select count(*) from t_article_comment where replay_ref_id = '0' and ref_article_id = #{articleId}"})
    int getArticleCommentNum(String articleId);

    /**
     * 根据refArticleCommentId更新评论
     * @param articleComment
     * @return
     */
    @UpdateProvider(type = ArticleProvider.class, method = "updateCommentByRefSelective")
    int updateByRefSelective(ArticleComment articleComment);

    /**
     * 获取某个文章的评论
     * @param articleId
     * @return
     */
    @Results(id = "commentInfo", value = {
            @Result(column="comment_id", property="commentId", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="ref_comment_id", property="refCommentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="approve", property="approve", jdbcType=JdbcType.INTEGER),
            @Result(column="floor", property="floor", jdbcType=JdbcType.INTEGER),
            @Result(column="ref_article_id", property="refArticleId", jdbcType=JdbcType.VARCHAR),
            @Result(column="replay_user", property="replayUser", jdbcType=JdbcType.VARCHAR),
            @Result(column="replay_to", property="replayTo", jdbcType=JdbcType.VARCHAR),
            @Result(column="replay_ref_id", property="replayRefId", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
            @Result(column="create_ip", property="createIp", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
            @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    @Select({"select * from t_article_comment " +
            " where replay_ref_id = '0' and ref_article_id = #{articleId}" +
            " order by create_date desc"})
    List<JSONObject> getOneArticleComment(String articleId);

    /**
     * 获取一天评论的回复
     * @param refCommentId
     * @return
     */
    @ResultMap(value = "commentInfo")
    @Select({"select * from t_article_comment where replay_ref_id = #{refCommentId}"})
    List<JSONObject> getByReplayRefId(String refCommentId);

    /**
     * 用户收到的评论
     * @param username
     * @return
     */
    @Select({"select ac.content,ac.create_date createDate,ac.replay_ref_id replayRefId,ac.ref_commentId commentId," +
            " u.nickname receiver,u.ref_user_id receiveUserId,a.ref_article_id articleId,a.title," +
            " u1.nickname replayName,u1.ref_user_id replayUserId " +
            " from t_article_comment ac " +
            " left join t_user u on u.ref_user_id = ac.replay_to " +
            " left join t_article a on ac.ref_article_id = a.ref_article_id" +
            " left join t_user u1 on u1.ref_user_id = ac.replay_user" +
            " where u.username = #{username} and ac.status = 0 " +
            " order by ac.create_date desc"})
    List<JSONObject> receive(String username);

    /**
     * 获取用户的评论历史
     * @param username
     * @return
     */
    @Select({"select ac.content,ac.create_date createDate,ac.replay_ref_id replayRefId,ac.refCommentId commentId,u.nickname replayName," +
            " u.ref_user_id replayUserId,u1.nickname replayToName,u1.ref_user_id replayToUserId," +
            " a.refArticleId articleId,a.title from t_article_comment ac " +
            " left join t_user u on u.ref_user_id = ac.replay_user " +
            " left join t_article a on ac.ref_article_id = a.ref_article_id" +
            " left join t_user u1 on u1.ref_user_id = ac.replay_to" +
            " where u.username = #{username} and ac.status = 0 " +
            " order by ac.create_date desc"})
    List<JSONObject> replayTo(String username);

}
