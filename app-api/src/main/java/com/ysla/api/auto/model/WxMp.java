package com.ysla.api.auto.model;

import java.io.Serializable;

/**
 * 
 *
 * @author konghang
 * @date 2018/09/27
 */
public class WxMp implements Serializable {
    /**
     * MpID
     */
    private Integer mpId;

    /**
     * 帐户
     */
    private String account;

    /**
     * 用户帐号
     */
    private String accountName;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 服务器认证Token
     */
    private String token;

    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    private Long expiresIn;

    /**
     * JSSDK
     */
    private String jsapiTicket;

    /**
     * JSSDK过期时间
     */
    private Long jsapiTicketExpiresIn;

    /**
     * JS接口安全域名
     */
    private String jsOauthUrl;

    /**
     * api_ticket，卡券接口中签名所需凭证
     */
    private String apiTicket;

    /**
     * api_ticket过期时间
     */
    private Long apiTicketExpiresIn;

    /**
     * 微信支付ID
     */
    private String mchId;

    /**
     * 微信支付密钥
     */
    private String wxPaySecret;

    /**
     * 
     */
    private String wxPayNotifyUrl;

    /**
     * 微信支付证书路径
     */
    private String wxPayCertPath;

    /**
     * WHO字段
     */
    private Long createDate;

    /**
     * WHO字段
     */
    private String createIp;

    /**
     * WHO字段
     */
    private String createBy;

    /**
     * WHO字段
     */
    private Long lastUpdateDate;

    /**
     * WHO字段
     */
    private String lastUpdateBy;

    /**
     * 微信登陆网页应用的appId
     */
    private String webAppId;

    /**
     * 微信登录网页应用的secret
     */
    private String webAppSecret;

    private static final long serialVersionUID = 1L;

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket == null ? null : jsapiTicket.trim();
    }

    public Long getJsapiTicketExpiresIn() {
        return jsapiTicketExpiresIn;
    }

    public void setJsapiTicketExpiresIn(Long jsapiTicketExpiresIn) {
        this.jsapiTicketExpiresIn = jsapiTicketExpiresIn;
    }

    public String getJsOauthUrl() {
        return jsOauthUrl;
    }

    public void setJsOauthUrl(String jsOauthUrl) {
        this.jsOauthUrl = jsOauthUrl == null ? null : jsOauthUrl.trim();
    }

    public String getApiTicket() {
        return apiTicket;
    }

    public void setApiTicket(String apiTicket) {
        this.apiTicket = apiTicket == null ? null : apiTicket.trim();
    }

    public Long getApiTicketExpiresIn() {
        return apiTicketExpiresIn;
    }

    public void setApiTicketExpiresIn(Long apiTicketExpiresIn) {
        this.apiTicketExpiresIn = apiTicketExpiresIn;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getWxPaySecret() {
        return wxPaySecret;
    }

    public void setWxPaySecret(String wxPaySecret) {
        this.wxPaySecret = wxPaySecret == null ? null : wxPaySecret.trim();
    }

    public String getWxPayNotifyUrl() {
        return wxPayNotifyUrl;
    }

    public void setWxPayNotifyUrl(String wxPayNotifyUrl) {
        this.wxPayNotifyUrl = wxPayNotifyUrl == null ? null : wxPayNotifyUrl.trim();
    }

    public String getWxPayCertPath() {
        return wxPayCertPath;
    }

    public void setWxPayCertPath(String wxPayCertPath) {
        this.wxPayCertPath = wxPayCertPath == null ? null : wxPayCertPath.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Long getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
    }

    public String getWebAppId() {
        return webAppId;
    }

    public void setWebAppId(String webAppId) {
        this.webAppId = webAppId == null ? null : webAppId.trim();
    }

    public String getWebAppSecret() {
        return webAppSecret;
    }

    public void setWebAppSecret(String webAppSecret) {
        this.webAppSecret = webAppSecret == null ? null : webAppSecret.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WxMp other = (WxMp) that;
        return (this.getMpId() == null ? other.getMpId() == null : this.getMpId().equals(other.getMpId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getAccessToken() == null ? other.getAccessToken() == null : this.getAccessToken().equals(other.getAccessToken()))
            && (this.getExpiresIn() == null ? other.getExpiresIn() == null : this.getExpiresIn().equals(other.getExpiresIn()))
            && (this.getJsapiTicket() == null ? other.getJsapiTicket() == null : this.getJsapiTicket().equals(other.getJsapiTicket()))
            && (this.getJsapiTicketExpiresIn() == null ? other.getJsapiTicketExpiresIn() == null : this.getJsapiTicketExpiresIn().equals(other.getJsapiTicketExpiresIn()))
            && (this.getJsOauthUrl() == null ? other.getJsOauthUrl() == null : this.getJsOauthUrl().equals(other.getJsOauthUrl()))
            && (this.getApiTicket() == null ? other.getApiTicket() == null : this.getApiTicket().equals(other.getApiTicket()))
            && (this.getApiTicketExpiresIn() == null ? other.getApiTicketExpiresIn() == null : this.getApiTicketExpiresIn().equals(other.getApiTicketExpiresIn()))
            && (this.getMchId() == null ? other.getMchId() == null : this.getMchId().equals(other.getMchId()))
            && (this.getWxPaySecret() == null ? other.getWxPaySecret() == null : this.getWxPaySecret().equals(other.getWxPaySecret()))
            && (this.getWxPayNotifyUrl() == null ? other.getWxPayNotifyUrl() == null : this.getWxPayNotifyUrl().equals(other.getWxPayNotifyUrl()))
            && (this.getWxPayCertPath() == null ? other.getWxPayCertPath() == null : this.getWxPayCertPath().equals(other.getWxPayCertPath()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateIp() == null ? other.getCreateIp() == null : this.getCreateIp().equals(other.getCreateIp()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getLastUpdateDate() == null ? other.getLastUpdateDate() == null : this.getLastUpdateDate().equals(other.getLastUpdateDate()))
            && (this.getLastUpdateBy() == null ? other.getLastUpdateBy() == null : this.getLastUpdateBy().equals(other.getLastUpdateBy()))
            && (this.getWebAppId() == null ? other.getWebAppId() == null : this.getWebAppId().equals(other.getWebAppId()))
            && (this.getWebAppSecret() == null ? other.getWebAppSecret() == null : this.getWebAppSecret().equals(other.getWebAppSecret()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMpId() == null) ? 0 : getMpId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
        result = prime * result + ((getExpiresIn() == null) ? 0 : getExpiresIn().hashCode());
        result = prime * result + ((getJsapiTicket() == null) ? 0 : getJsapiTicket().hashCode());
        result = prime * result + ((getJsapiTicketExpiresIn() == null) ? 0 : getJsapiTicketExpiresIn().hashCode());
        result = prime * result + ((getJsOauthUrl() == null) ? 0 : getJsOauthUrl().hashCode());
        result = prime * result + ((getApiTicket() == null) ? 0 : getApiTicket().hashCode());
        result = prime * result + ((getApiTicketExpiresIn() == null) ? 0 : getApiTicketExpiresIn().hashCode());
        result = prime * result + ((getMchId() == null) ? 0 : getMchId().hashCode());
        result = prime * result + ((getWxPaySecret() == null) ? 0 : getWxPaySecret().hashCode());
        result = prime * result + ((getWxPayNotifyUrl() == null) ? 0 : getWxPayNotifyUrl().hashCode());
        result = prime * result + ((getWxPayCertPath() == null) ? 0 : getWxPayCertPath().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateIp() == null) ? 0 : getCreateIp().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdateBy() == null) ? 0 : getLastUpdateBy().hashCode());
        result = prime * result + ((getWebAppId() == null) ? 0 : getWebAppId().hashCode());
        result = prime * result + ((getWebAppSecret() == null) ? 0 : getWebAppSecret().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mpId=").append(mpId);
        sb.append(", account=").append(account);
        sb.append(", accountName=").append(accountName);
        sb.append(", appId=").append(appId);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", token=").append(token);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", expiresIn=").append(expiresIn);
        sb.append(", jsapiTicket=").append(jsapiTicket);
        sb.append(", jsapiTicketExpiresIn=").append(jsapiTicketExpiresIn);
        sb.append(", jsOauthUrl=").append(jsOauthUrl);
        sb.append(", apiTicket=").append(apiTicket);
        sb.append(", apiTicketExpiresIn=").append(apiTicketExpiresIn);
        sb.append(", mchId=").append(mchId);
        sb.append(", wxPaySecret=").append(wxPaySecret);
        sb.append(", wxPayNotifyUrl=").append(wxPayNotifyUrl);
        sb.append(", wxPayCertPath=").append(wxPayCertPath);
        sb.append(", createDate=").append(createDate);
        sb.append(", createIp=").append(createIp);
        sb.append(", createBy=").append(createBy);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", webAppId=").append(webAppId);
        sb.append(", webAppSecret=").append(webAppSecret);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}