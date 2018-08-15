package com.ysla.provider.module.wx.user.dao;

import com.ysla.api.auto.mapper.WxUserMapper;
import com.ysla.api.auto.model.WxUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信用户mapper扩展类
 * @author konghang
 */
@Mapper
public interface IWxUserDao extends WxUserMapper {

    /**
     * 插入微信用户信息,如果存在则更新
     * @param record
     * @return
     */
    @Insert({"insert into wx_user (app_id, open_id,union_Id, nickname,sex, province, city," +
            "country, `language`, head_img_url, subscribe, subscribe_time, create_date,last_update_date)" +
            "values (#{appId}, #{openId}, #{unionId}, #{nickname}, #{sex}, #{province}, #{city}," +
            "#{country}, #{language}, #{headImgUrl}, #{subscribe}, #{subscribeTime}, #{createDate}," +
            "#{lastUpdateDate}) ON DUPLICATE KEY UPDATE " +
            "nickname = #{nickname},sex = #{sex},province = #{province},city = #{city}," +
            "country = #{country},`language` = #{language},head_img_url = #{headImgUrl},subscribe = #{subscribe}," +
            "subscribe_time = #{subscribeTime},last_update_date = #{lastUpdateDate}"})
    int insertUser(WxUser record);

}
