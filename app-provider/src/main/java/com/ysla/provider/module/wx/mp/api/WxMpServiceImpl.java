package com.ysla.provider.module.wx.mp.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.ysla.api.auto.model.WxMp;
import com.ysla.api.module.wx.mp.IApiService;
import com.ysla.api.module.wx.mp.IWxMpService;
import com.ysla.provider.module.wx.mp.dao.IWxMpDao;
import com.ysla.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 微信公众号api实现
 * @author konghang
 */
@Slf4j
@Component
@Service(version = "${dubbo.service.version}")
public class WxMpServiceImpl implements IWxMpService {

    @Resource
    private IWxMpDao mpDao;
    @Resource
    private IApiService apiService;

    /**
     * 获得指定的微信公众号
     * @param appId
     * @return
     */
    @Override
    public WxMp getWxMp(String appId) {
        return mpDao.selectWxMpByAppId(appId);
    }

    /**
     * 更新公众号信息
     * @param mp
     * @return
     */
    @Override
    public boolean updateMp(WxMp mp) {
        long currentTimestamp = DateUtils.getUnixStamp();
        mp.setLastUpdateDate(currentTimestamp);
        return mpDao.updateByPrimaryKeySelective(mp) > 0 ? true : false;
    }

    /**
     * 获取公众号的基础AccessToken
     * @param appId
     * @return
     */
    @Override
    public String getAccessToken(String appId) {
        WxMp mp = getWxMp(appId);
        long currentTimestamp = DateUtils.getUnixStamp();
        if (mp.getAccessToken() == null || "".equals(mp.getAccessToken()) ||
                currentTimestamp > mp.getExpiresIn()) {
            //更新AccessToken
            if (apiService.refreshAccessToken(mp) == false) {
                log.error("refreshAccessToken Fail:{}, retry again:{}",currentTimestamp, apiService.refreshAccessToken(mp));
            }
        }
        return mp.getAccessToken();
    }

    /**
     * 获取jsApiTicket
     * @param appId
     * @return
     */
    @Override
    public String getJsApiTicket(String appId) {
        WxMp mp = getWxMp(appId);
        long currentTimestamp = DateUtils.getUnixStamp();
        if (mp.getJsapiTicket() == null || "".equals(mp.getJsapiTicket()) ||
                currentTimestamp > mp.getJsapiTicketExpiresIn()) {
            //更新AccessToken
            if (apiService.refreshJsApiTicket(mp) == false) {
                log.error("refreshAccessToken Fail:{}, retry again:{}",currentTimestamp,apiService.refreshJsApiTicket(mp));
            }
        }
        return mp.getJsapiTicket();
    }
    
    @Override
    public String getApiTicket(String appId) {
        WxMp mp = getWxMp(appId);
        long currentTimestamp = DateUtils.getUnixStamp();
        if (mp.getApiTicket() == null || "".equals(mp.getApiTicket()) ||
                currentTimestamp > mp.getApiTicketExpiresIn()) {
            if (apiService.refreshApiTicket(mp) == false) {
                log.error("refreshApiTicket Fail:{},retry again:{}", currentTimestamp, apiService.refreshApiTicket(mp));
            }
        }
        return mp.getApiTicket();
    }
}
