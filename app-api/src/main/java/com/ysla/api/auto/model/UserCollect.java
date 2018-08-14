package com.ysla.api.auto.model;

import java.io.Serializable;

public class UserCollect implements Serializable {
    private Integer collectId;

    private String refCollectId;

    private String userId;

    private String linkId;

    private Byte status;

    private Byte type;

    private Long createDate;

    private static final long serialVersionUID = 1L;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public String getRefCollectId() {
        return refCollectId;
    }

    public void setRefCollectId(String refCollectId) {
        this.refCollectId = refCollectId == null ? null : refCollectId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId == null ? null : linkId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
        UserCollect other = (UserCollect) that;
        return (this.getCollectId() == null ? other.getCollectId() == null : this.getCollectId().equals(other.getCollectId()))
            && (this.getRefCollectId() == null ? other.getRefCollectId() == null : this.getRefCollectId().equals(other.getRefCollectId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLinkId() == null ? other.getLinkId() == null : this.getLinkId().equals(other.getLinkId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCollectId() == null) ? 0 : getCollectId().hashCode());
        result = prime * result + ((getRefCollectId() == null) ? 0 : getRefCollectId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLinkId() == null) ? 0 : getLinkId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", collectId=").append(collectId);
        sb.append(", refCollectId=").append(refCollectId);
        sb.append(", userId=").append(userId);
        sb.append(", linkId=").append(linkId);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}