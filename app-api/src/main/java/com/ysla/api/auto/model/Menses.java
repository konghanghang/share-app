package com.ysla.api.auto.model;

import java.io.Serializable;

/**
 * 
 *
 * @author konghang
 * @date 2018/09/27
 */
public class Menses implements Serializable {
    /**
     * 主键Id,自增
     */
    private Integer mensesId;

    /**
     * 年
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 上一次月经时间
     */
    private Long lastMensesTime;

    /**
     * 预测这一次的时间
     */
    private Long mensesTime;

    /**
     * 这一次真正的月经时间
     */
    private Long trueMensesTime;

    /**
     * 创建时间
     */
    private Long createDate;

    private static final long serialVersionUID = 1L;

    public Integer getMensesId() {
        return mensesId;
    }

    public void setMensesId(Integer mensesId) {
        this.mensesId = mensesId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Long getLastMensesTime() {
        return lastMensesTime;
    }

    public void setLastMensesTime(Long lastMensesTime) {
        this.lastMensesTime = lastMensesTime;
    }

    public Long getMensesTime() {
        return mensesTime;
    }

    public void setMensesTime(Long mensesTime) {
        this.mensesTime = mensesTime;
    }

    public Long getTrueMensesTime() {
        return trueMensesTime;
    }

    public void setTrueMensesTime(Long trueMensesTime) {
        this.trueMensesTime = trueMensesTime;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
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
        Menses other = (Menses) that;
        return (this.getMensesId() == null ? other.getMensesId() == null : this.getMensesId().equals(other.getMensesId()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getLastMensesTime() == null ? other.getLastMensesTime() == null : this.getLastMensesTime().equals(other.getLastMensesTime()))
            && (this.getMensesTime() == null ? other.getMensesTime() == null : this.getMensesTime().equals(other.getMensesTime()))
            && (this.getTrueMensesTime() == null ? other.getTrueMensesTime() == null : this.getTrueMensesTime().equals(other.getTrueMensesTime()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMensesId() == null) ? 0 : getMensesId().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getLastMensesTime() == null) ? 0 : getLastMensesTime().hashCode());
        result = prime * result + ((getMensesTime() == null) ? 0 : getMensesTime().hashCode());
        result = prime * result + ((getTrueMensesTime() == null) ? 0 : getTrueMensesTime().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mensesId=").append(mensesId);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", openId=").append(openId);
        sb.append(", lastMensesTime=").append(lastMensesTime);
        sb.append(", mensesTime=").append(mensesTime);
        sb.append(", trueMensesTime=").append(trueMensesTime);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}