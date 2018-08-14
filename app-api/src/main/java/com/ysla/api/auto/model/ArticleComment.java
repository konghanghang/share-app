package com.ysla.api.auto.model;

import java.io.Serializable;

public class ArticleComment implements Serializable {
    private Integer commentId;

    private String refCommentId;

    private Integer approve;

    private Integer floor;

    private String refArticleId;

    private String replayUser;

    private String replayTo;

    private String replayRefId;

    private Byte status;

    private String createIp;

    private Long createDate;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getRefCommentId() {
        return refCommentId;
    }

    public void setRefCommentId(String refCommentId) {
        this.refCommentId = refCommentId == null ? null : refCommentId.trim();
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getRefArticleId() {
        return refArticleId;
    }

    public void setRefArticleId(String refArticleId) {
        this.refArticleId = refArticleId == null ? null : refArticleId.trim();
    }

    public String getReplayUser() {
        return replayUser;
    }

    public void setReplayUser(String replayUser) {
        this.replayUser = replayUser == null ? null : replayUser.trim();
    }

    public String getReplayTo() {
        return replayTo;
    }

    public void setReplayTo(String replayTo) {
        this.replayTo = replayTo == null ? null : replayTo.trim();
    }

    public String getReplayRefId() {
        return replayRefId;
    }

    public void setReplayRefId(String replayRefId) {
        this.replayRefId = replayRefId == null ? null : replayRefId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        ArticleComment other = (ArticleComment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getRefCommentId() == null ? other.getRefCommentId() == null : this.getRefCommentId().equals(other.getRefCommentId()))
            && (this.getApprove() == null ? other.getApprove() == null : this.getApprove().equals(other.getApprove()))
            && (this.getFloor() == null ? other.getFloor() == null : this.getFloor().equals(other.getFloor()))
            && (this.getRefArticleId() == null ? other.getRefArticleId() == null : this.getRefArticleId().equals(other.getRefArticleId()))
            && (this.getReplayUser() == null ? other.getReplayUser() == null : this.getReplayUser().equals(other.getReplayUser()))
            && (this.getReplayTo() == null ? other.getReplayTo() == null : this.getReplayTo().equals(other.getReplayTo()))
            && (this.getReplayRefId() == null ? other.getReplayRefId() == null : this.getReplayRefId().equals(other.getReplayRefId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateIp() == null ? other.getCreateIp() == null : this.getCreateIp().equals(other.getCreateIp()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getRefCommentId() == null) ? 0 : getRefCommentId().hashCode());
        result = prime * result + ((getApprove() == null) ? 0 : getApprove().hashCode());
        result = prime * result + ((getFloor() == null) ? 0 : getFloor().hashCode());
        result = prime * result + ((getRefArticleId() == null) ? 0 : getRefArticleId().hashCode());
        result = prime * result + ((getReplayUser() == null) ? 0 : getReplayUser().hashCode());
        result = prime * result + ((getReplayTo() == null) ? 0 : getReplayTo().hashCode());
        result = prime * result + ((getReplayRefId() == null) ? 0 : getReplayRefId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateIp() == null) ? 0 : getCreateIp().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", refCommentId=").append(refCommentId);
        sb.append(", approve=").append(approve);
        sb.append(", floor=").append(floor);
        sb.append(", refArticleId=").append(refArticleId);
        sb.append(", replayUser=").append(replayUser);
        sb.append(", replayTo=").append(replayTo);
        sb.append(", replayRefId=").append(replayRefId);
        sb.append(", status=").append(status);
        sb.append(", createIp=").append(createIp);
        sb.append(", createDate=").append(createDate);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}