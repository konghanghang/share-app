package com.ysla.provider.module.data.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.IpInfo;
import com.ysla.api.module.data.IpInfoService;
import com.ysla.api.utils.http.HttpClientUtils;
import com.ysla.api.utils.http.IpUtils;
import com.ysla.provider.module.data.dao.IpInfoDao;
import com.ysla.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ip信息实现
 * @author konghang
 */
@Slf4j
@Component
@Service(version = "${dubbo.service.version}")
public class IpInfoServiceImpl implements IpInfoService {

    private String apiUrl = "http://ip.taobao.com/service/getIpInfo.php?ip={{IP}}";

    @Resource
    private IpInfoDao ipInfoDao;

    /**
     * 根据ip地址获取ip详细信息
     * @param ip
     * @return
     */
    @Override
    public IpInfo getIpLocation(String ip) {
        if (ip == null) {
            return null;
        }
        Long longIp = IpUtils.ipToLong(ip);
        IpInfo dataIp = ipInfoDao.selectByPrimaryKey(longIp);
        if (dataIp == null) {
            dataIp = loadIpLocation(ip);
        }
        return dataIp;
    }

    /**
     * 获取ip详细信息
     * @param ip
     * @return
     */
    private IpInfo loadIpLocation(String ip) {
        JSONObject jsonObject = HttpClientUtils.httpGet(apiUrl.replace("{{IP}}", ip));
        IpInfo dataIp = parseIpAddr(jsonObject);
        Long longIp = IpUtils.ipToLong(ip);
        dataIp.setIp(longIp);
        dataIp.setCreateDate(DateUtils.getUnixStamp());
        dataIp.setLastUpdateDate(dataIp.getCreateDate());
        int effectRow = ipInfoDao.insert(dataIp);
        if (effectRow == 0) {
            log.error("add ip Address Fail:" + ip);
        }
        return dataIp;
    }

    /**
     * 解析json到ipInfo对象中
     * @param jsonObject
     * @return
     */
    private IpInfo parseIpAddr(JSONObject jsonObject) {
        Integer errorCode = jsonObject.getInteger("code");
        if (errorCode == 0) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                IpInfo dataIp = new IpInfo();
                dataIp.setAreaId(data.getString("area_id"));
                dataIp.setArea(data.getString("area"));
                dataIp.setCountryId(data.getString("country_id"));
                dataIp.setCountry(data.getString("country"));
                dataIp.setRegionId(data.getString("region_id"));
                dataIp.setRegion(data.getString("region"));
                dataIp.setCityId(data.getString("city_id"));
                dataIp.setCity(data.getString("city"));
                dataIp.setCountyId(data.getString("county_id"));
                dataIp.setCounty(data.getString("county"));
                dataIp.setIspId(data.getString("isp_id"));
                dataIp.setIsp(data.getString("isp"));
                return dataIp;
            }
        } else {
            log.error("Get Ip Address Data Fail:" + errorCode + ", reason:" + jsonObject.getString("reason"));
        }

        return null;
    }

}
