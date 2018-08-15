package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.ArticleComment;
import org.apache.ibatis.jdbc.SQL;

public class ArticleCommentSqlProvider {

    public String insertSelective(ArticleComment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_article_comment");
        
        if (record.getRefCommentId() != null) {
            sql.VALUES("ref_comment_id", "#{refCommentId,jdbcType=VARCHAR}");
        }
        
        if (record.getApprove() != null) {
            sql.VALUES("approve", "#{approve,jdbcType=INTEGER}");
        }
        
        if (record.getFloor() != null) {
            sql.VALUES("`floor`", "#{floor,jdbcType=INTEGER}");
        }
        
        if (record.getRefArticleId() != null) {
            sql.VALUES("ref_article_id", "#{refArticleId,jdbcType=VARCHAR}");
        }
        
        if (record.getReplayUser() != null) {
            sql.VALUES("replay_user", "#{replayUser,jdbcType=VARCHAR}");
        }
        
        if (record.getReplayTo() != null) {
            sql.VALUES("replay_to", "#{replayTo,jdbcType=VARCHAR}");
        }
        
        if (record.getReplayRefId() != null) {
            sql.VALUES("replay_ref_id", "#{replayRefId,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("`status`", "#{status,jdbcType=TINYINT}");
        }
        
        if (record.getCreateIp() != null) {
            sql.VALUES("create_ip", "#{createIp,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ArticleComment record) {
        SQL sql = new SQL();
        sql.UPDATE("t_article_comment");
        
        if (record.getRefCommentId() != null) {
            sql.SET("ref_comment_id = #{refCommentId,jdbcType=VARCHAR}");
        }
        
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
            sql.SET("replay_ref_id = #{replayRefId,jdbcType=VARCHAR}");
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
        
        sql.WHERE("comment_id = #{commentId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}