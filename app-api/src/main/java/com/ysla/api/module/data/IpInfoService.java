package com.ysla.api.module.data;

import com.ysla.api.auto.model.IpInfo;

/**
 * ip信息api
 * @author konghang
 */
public interface IpInfoService {

    /**
     * 根据ip地址获取ip详细信息
     * @param ip
     * @return
     */
    IpInfo getIpLocation(String ip);

}
