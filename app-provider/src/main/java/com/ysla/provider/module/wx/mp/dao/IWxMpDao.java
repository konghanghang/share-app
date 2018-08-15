package com.ysla.provider.module.wx.mp.dao;

import com.ysla.api.auto.mapper.WxMpMapper;
import com.ysla.api.auto.model.WxMp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 微信公众号mapper扩展类
 * @author konghang
 */
@Mapper
public interface IWxMpDao extends WxMpMapper {

    /**
     * 通过AppId查询Mp信息
     * @param appId
     * @return
     */
    @Select({"select * from wx_mp where app_id = #{appId}"})
    WxMp selectWxMpByAppId(String appId);

    /**
     * 获取所有公众号信息
     * @param account
     * @param accountName
     * @return
     */
    @Select({"select * from wx_mp  where (#{account} is null or account = #{account} ) ",
            "and (#{accountName} is null or accountName = #{accountName})"})
    List<WxMp> getWxMpList(@Param("account") String account,
                           @Param("accountName") String accountName);

}
