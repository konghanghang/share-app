package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.ArticleComment;
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

public interface ArticleCommentMapper {
    @Delete({
        "delete from t_article_comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer commentId);

    @Insert({
        "insert into t_article_comment (ref_comment_id, approve, ",
        "`floor`, ref_article_id, ",
        "replay_user, replay_to, ",
        "replay_ref_id, `status`, ",
        "create_ip, create_date, ",
        "content)",
        "values (#{refCommentId,jdbcType=VARCHAR}, #{approve,jdbcType=INTEGER}, ",
        "#{floor,jdbcType=INTEGER}, #{refArticleId,jdbcType=VARCHAR}, ",
        "#{replayUser,jdbcType=VARCHAR}, #{replayTo,jdbcType=VARCHAR}, ",
        "#{replayRefId,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ",
        "#{createIp,jdbcType=VARCHAR}, #{createDate,jdbcType=BIGINT}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="commentId", before=false, resultType=Integer.class)
    int insert(ArticleComment record);

    @InsertProvider(type=ArticleCommentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="commentId", before=false, resultType=Integer.class)
    int insertSelective(ArticleComment record);

    @Select({
        "select",
        "comment_id, ref_comment_id, approve, `floor`, ref_article_id, replay_user, replay_to, ",
        "replay_ref_id, `status`, create_ip, create_date, content",
        "from t_article_comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER, id=true),
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
    ArticleComment selectByPrimaryKey(Integer commentId);

    @UpdateProvider(type=ArticleCommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ArticleComment record);

    @Update({
        "update t_article_comment",
        "set ref_comment_id = #{refCommentId,jdbcType=VARCHAR},",
          "approve = #{approve,jdbcType=INTEGER},",
          "`floor` = #{floor,jdbcType=INTEGER},",
          "ref_article_id = #{refArticleId,jdbcType=VARCHAR},",
          "replay_user = #{replayUser,jdbcType=VARCHAR},",
          "replay_to = #{replayTo,jdbcType=VARCHAR},",
          "replay_ref_id = #{replayRefId,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT},",
          "create_ip = #{createIp,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ArticleComment record);

    @Update({
        "update t_article_comment",
        "set ref_comment_id = #{refCommentId,jdbcType=VARCHAR},",
          "approve = #{approve,jdbcType=INTEGER},",
          "`floor` = #{floor,jdbcType=INTEGER},",
          "ref_article_id = #{refArticleId,jdbcType=VARCHAR},",
          "replay_user = #{replayUser,jdbcType=VARCHAR},",
          "replay_to = #{replayTo,jdbcType=VARCHAR},",
          "replay_ref_id = #{replayRefId,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=TINYINT},",
          "create_ip = #{createIp,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT}",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ArticleComment record);
}