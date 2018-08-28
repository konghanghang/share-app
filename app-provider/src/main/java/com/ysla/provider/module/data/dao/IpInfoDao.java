package com.ysla.provider.module.data.dao;

import com.ysla.api.auto.mapper.IpInfoMapper;
import com.ysla.api.auto.model.IpInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ip信息mapper扩展类
 * @author konghang
 */
@Mapper
public interface IpInfoDao extends IpInfoMapper {

    /**
     * inet_aton函数可以将String类型的ip(127.0.0.1)转换成long类型的数字
     * @param ip
     * @return
     */
    @Select({"select * from s_ip_info where ip = inet_aton(#{ip})"})
    IpInfo selectByIp(@Param("ip") String ip);

}
