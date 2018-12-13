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
    @Select({"select count(*) from t_article_comment where replay_id = '0' and article_id = #{articleId}"})
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
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="comment_id", property="commentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="approve", property="approve", jdbcType=JdbcType.INTEGER),
            @Result(column="floor", property="floor", jdbcType=JdbcType.INTEGER),
            @Result(column="article_id", property="articleId", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_user", property="createUser", jdbcType=JdbcType.VARCHAR),
            @Result(column="replay_to", property="replayTo", jdbcType=JdbcType.VARCHAR),
            @Result(column="replay_id", property="replayId", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
            @Result(column="create_ip", property="createIp", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
            @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    @Select({"select * from t_article_comment " +
            " where replay_id = '0' and article_id = #{articleId}" +
            " order by create_date desc"})
    List<JSONObject> getOneArticleComment(String articleId);

    /**
     * 获取一天评论的回复
     * @param commentId
     * @return
     */
    @ResultMap(value = "commentInfo")
    @Select({"select * from t_article_comment where replay_id = #{commentId}"})
    List<JSONObject> getByReplayRefId(String commentId);

    /**
     * 用户收到的评论
     * @param username
     * @return
     */
    @Select({"select ac.content,ac.create_date createDate,ac.replay_id replayRefId,ac.comment_id commentId," +
            " u.nickname receiver,u.user_id receiveUserId,a.article_id articleId,a.title," +
            " u1.nickname replayName,u1.user_id replayUserId " +
            " from t_article_comment ac " +
            " left join t_user u on u.user_id = ac.replay_to " +
            " left join t_article a on ac.article_id = a.article_id" +
            " left join t_user u1 on u1.user_id = ac.replay_user" +
            " where u.username = #{username} and ac.status = 0 " +
            " order by ac.create_date desc"})
    List<JSONObject> receive(String username);

    /**
     * 获取用户的评论历史
     * @param username
     * @return
     */
    @Select({"select ac.content,ac.create_date createDate,ac.replay_id replayRefId,ac.comment_id commentId,u.nickname replayName," +
            " u.ref_user_id replayUserId,u1.nickname replayToName,u1.user_id replayToUserId," +
            " a.article_id articleId,a.title from t_article_comment ac " +
            " left join t_user u on u.user_id = ac.replay_user " +
            " left join t_article a on ac.article_id = a.article_id" +
            " left join t_user u1 on u1.user_id = ac.replay_to" +
            " where u.username = #{username} and ac.status = 0 " +
            " order by ac.create_date desc"})
    List<JSONObject> replayTo(String username);

    /**
     * 更新评论点赞数
     * @param commentId
     * @param num
     * @return
     */
    @Update({"update t_article_comment set approve = approve + #{num} where comment_id = #{commentId}"})
    int updateApprove(@Param("commentId") String commentId,@Param("num") Integer num);

}
