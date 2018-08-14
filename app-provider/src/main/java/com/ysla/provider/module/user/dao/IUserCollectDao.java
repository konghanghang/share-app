package com.ysla.provider.module.user.dao;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.mapper.UserCollectMapper;
import com.ysla.api.auto.model.UserCollect;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户收藏mapper扩展类
 * @author konghang
 */
@Mapper
public interface IUserCollectDao extends UserCollectMapper {

    /**
     * 收藏
     * @param userCollect
     * @return
     */
    @Insert({"insert into t_user_collect (ref_collect_id, user_id, link_id, `status`, type, create_date)",
            "values (#{collect.refCollectId}, #{collect.userId}, #{collect.linkId}, #{collect.status}, ",
            "#{collect.type},#{collect.createDate}) ON DUPLICATE KEY UPDATE status = (status + 1) % 2 "})
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="collectId", before=false, resultType=Integer.class)
    int collect(@Param("collect") UserCollect userCollect);

    /**
     * 通过refId获取收藏详情
     * @param collectId
     * @return
     */
    @Select({"select * from t_user_collect where ref_collect_id = #{collectId}"})
    UserCollect selectByRef(String collectId);

    /**
     * 用户收藏的文章列表
     * @param username
     * @return
     */
    @Select({"select a.refArticleId articleId,a.title,uc.refCollectId collectId " +
            " from t_user_collect uc " +
            " left join t_article a on uc.link_id = a.ref_article_id " +
            " left join t_user u on u.ref_user_id = uc.user_id " +
            " where u.username = #{username} and uc.status = 0 and uc.type = 1 " +
            " order by uc.create_date desc"})
    List<JSONObject> articleCollects(String username);

}
