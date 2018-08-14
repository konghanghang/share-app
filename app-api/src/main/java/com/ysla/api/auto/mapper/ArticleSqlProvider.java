package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.Article;
import org.apache.ibatis.jdbc.SQL;

public class ArticleSqlProvider {

    public String insertSelective(Article record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_article");
        
        if (record.getRefArticleId() != null) {
            sql.VALUES("ref_article_id", "#{refArticleId,jdbcType=VARCHAR}");
        }
        
        if (record.getRefUserId() != null) {
            sql.VALUES("ref_user_id", "#{refUserId,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getCoverImage() != null) {
            sql.VALUES("cover_image", "#{coverImage,jdbcType=VARCHAR}");
        }
        
        if (record.getCountView() != null) {
            sql.VALUES("count_view", "#{countView,jdbcType=INTEGER}");
        }
        
        if (record.getCountComment() != null) {
            sql.VALUES("count_comment", "#{countComment,jdbcType=INTEGER}");
        }
        
        if (record.getCountCollection() != null) {
            sql.VALUES("count_collection", "#{countCollection,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("`type`", "#{type,jdbcType=VARCHAR}");
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
        
        if (record.getUpdateDate() != null) {
            sql.VALUES("update_date", "#{updateDate,jdbcType=BIGINT}");
        }
        
        if (record.getMdContent() != null) {
            sql.VALUES("md_content", "#{mdContent,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Article record) {
        SQL sql = new SQL();
        sql.UPDATE("t_article");
        
        if (record.getRefArticleId() != null) {
            sql.SET("ref_article_id = #{refArticleId,jdbcType=VARCHAR}");
        }
        
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
        
        sql.WHERE("article_id = #{articleId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}