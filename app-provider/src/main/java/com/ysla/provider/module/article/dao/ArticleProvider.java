package com.ysla.provider.module.article.dao;

import com.ysla.api.auto.model.Article;
import com.ysla.api.auto.model.ArticleComment;
import org.apache.ibatis.jdbc.SQL;

/**
 * 文章查询动态sql
 * @author konghang
 */
public class ArticleProvider {

    /**
     * 获取所有文章
     * @param username
     * @return
     */
    public String getArticles(String username){
        SQL sql = new SQL();
        sql.SELECT("a.article_id","a.ref_article_id","u.nickname author","a.title","a.count_view","a.count_comment");
        sql.SELECT("a.count_collection","a.type","a.create_date","a.cover_image","a.description");
        sql.FROM("t_article a");
        sql.LEFT_OUTER_JOIN("t_user u");
        return sql.toString();
    }

    /**
     * 更新文章通过refId
     * @param record
     * @return
     */
    public String updateByArticleRefSelective(Article record){
        SQL sql = new SQL();

        sql.UPDATE("t_article");

        if (record.getRefUserId() != null) {
            sql.SET("ref_user_id = #{refUserId,jdbcType=VARCHAR}");
        }
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        if (record.getCoverImage() != null) {
            sql.SET("cover_image = #{coverImage,jdbcType=VARCHAR}");
        }
        if (record.getCountView() != null) {
            sql.SET("count_view = #{countView,jdbcType=INTEGER}");
        }
        if (record.getCountComment() != null) {
            sql.SET("count_comment = #{countComment,jdbcType=INTEGER}");
        }
        if (record.getCountCollection() != null) {
            sql.SET("count_collection = #{countCollection,jdbcType=INTEGER}");
        }
        if (record.getType() != null) {
            sql.SET("`type` = #{type,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.SET("`status` = #{status,jdbcType=TINYINT}");
        }
        if (record.getCreateIp() != null) {
            sql.SET("create_ip = #{createIp,jdbcType=VARCHAR}");
        }
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        if (record.getUpdateDate() != null) {
            sql.SET("update_date = #{updateDate,jdbcType=BIGINT}");
        }
        if (record.getMdContent() != null) {
            sql.SET("md_content = #{mdContent,jdbcType=LONGVARCHAR}");
        }
        sql.WHERE("ref_article_id = #{refArticleId}");
        return sql.toString();
    }

    /**
     * 更新文章评论通过refId
     * @param record
     * @return
     */
    public String updateCommentByRefSelective(ArticleComment record) {
        SQL sql = new SQL();
        sql.UPDATE("t_article_comment");

        if (record.getApprove() != null) {
            sql.SET("approve = #{approve,jdbcType=INTEGER}");
        }
        if (record.getFloor() != null) {
            sql.SET("`floor` = #{floor,jdbcType=INTEGER}");
        }
        if (record.getRefArticleId() != null) {
            sql.SET("ref_article_id = #{refArticleId,jdbcType=VARCHAR}");
        }
        if (record.getReplayUser() != null) {
            sql.SET("replay_user = #{replayUser,jdbcType=VARCHAR}");
        }
        if (record.getReplayTo() != null) {
            sql.SET("replay_to = #{replayTo,jdbcType=VARCHAR}");
        }
        if (record.getReplayRefId() != null) {
            sql.SET("replay_ref_d = #{replayRefD,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.SET("`status` = #{status,jdbcType=TINYINT}");
        }
        if (record.getCreateIp() != null) {
            sql.SET("create_ip = #{createIp,jdbcType=VARCHAR}");
        }
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        sql.WHERE("ref_comment_id = #{refCommentId,jdbcType=VARCHAR}");

        return sql.toString();
    }

    /**
     * 更新文章统计数据
     * @param record
     * @return
     */
    public String updateStatistics(Article record){
        SQL sql = new SQL();
        sql.UPDATE("t_article");
        if (record.getCountView() != null) {
            sql.SET("count_view = count_view + #{countView}");
        }
        if (record.getCountComment() != null) {
            sql.SET("count_comment = count_comment + #{countComment}");
        }
        if (record.getCountCollection() != null) {
            sql.SET("count_collection = count_collection + #{countCollection}");
        }
        sql.WHERE("ref_article_id = #{refArticleId}");
        return sql.toString();
    }

}
