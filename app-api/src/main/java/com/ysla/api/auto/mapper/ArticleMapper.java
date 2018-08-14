package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ArticleMapper {
    @Delete({
        "delete from t_article",
        "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer articleId);

    @Insert({
        "insert into t_article (ref_article_id, ref_user_id, ",
        "title, description, ",
        "content, cover_image, ",
        "count_view, count_comment, ",
        "count_collection, `type`, ",
        "`status`, create_ip, ",
        "create_date, update_date, ",
        "md_content)",
        "values (#{refArticleId,jdbcType=VARCHAR}, #{refUserId,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{coverImage,jdbcType=VARCHAR}, ",
        "#{countView,jdbcType=INTEGER}, #{countComment,jdbcType=INTEGER}, ",
        "#{countCollection,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=TINYINT}, #{createIp,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=BIGINT}, #{updateDate,jdbcType=BIGINT}, ",
        "#{mdContent,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="articleId", before=false, resultType=Integer.class)
    int insert(Article record);

    @InsertProvider(type=ArticleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="articleId", before=false, resultType=Integer.class)
    int insertSelective(Article record);

    @Select({
        "select",
        "article_id, ref_article_id, ref_user_id, title, description, content, cover_image, ",
        "count_view, count_comment, count_collection, `type`, `status`, create_ip, create_date, ",
        "update_date, md_content",
        "from t_article",
        "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ref_article_id", property="refArticleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="ref_user_id", property="refUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_image", property="coverImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="count_view", property="countView", jdbcType=JdbcType.INTEGER),
        @Result(column="count_comment", property="countComment", jdbcType=JdbcType.INTEGER),
        @Result(column="count_collection", property="countCollection", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_ip", property="createIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.BIGINT),
        @Result(column="md_content", property="mdContent", jdbcType=JdbcType.LONGVARCHAR)
    })
    Article selectByPrimaryKey(Integer articleId);

    @UpdateProvider(type=ArticleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Article record);

    @Update({
        "update t_article",
        "set ref_article_id = #{refArticleId,jdbcType=VARCHAR},",
          "ref_user_id = #{refUserId,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "cover_image = #{coverImage,jdbcType=VARCHAR},",
          "count_view = #{countView,jdbcType=INTEGER},",
          "count_comment = #{countComment,jdbcType=INTEGER},",
          "count_collection = #{countCollection,jdbcType=INTEGER},",
          "`type` = #{type,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT},",
          "create_ip = #{createIp,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT},",
          "update_date = #{updateDate,jdbcType=BIGINT},",
          "md_content = #{mdContent,jdbcType=LONGVARCHAR}",
        "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Article record);

    @Update({
        "update t_article",
        "set ref_article_id = #{refArticleId,jdbcType=VARCHAR},",
          "ref_user_id = #{refUserId,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "cover_image = #{coverImage,jdbcType=VARCHAR},",
          "count_view = #{countView,jdbcType=INTEGER},",
          "count_comment = #{countComment,jdbcType=INTEGER},",
          "count_collection = #{countCollection,jdbcType=INTEGER},",
          "`type` = #{type,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT},",
          "create_ip = #{createIp,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT},",
          "update_date = #{updateDate,jdbcType=BIGINT}",
        "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Article record);
}