package com.ysla.api.auto.model;

import java.io.Serializable;

/**
 * 
 *
 * @author konghang
 * @date 2018/09/27
 */
public class Article implements Serializable {
    /**
     * 
     */
    private Integer articleId;

    /**
     * 
     */
    private String refArticleId;

    /**
     * 用户(作者)refUserId
     */
    private String refUserId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介
     */
    private String description;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 封面图片,加上缩略图路径70个字段足够
     */
    private String coverImage;

    /**
     * 浏览总数
     */
    private Integer countView;

    /**
     * 评论总数
     */
    private Integer countComment;

    /**
     * 收藏总数
     */
    private Integer countCollection;

    /**
     * 文章分类
     */
    private String type;

    /**
     * 文章状态,0:正常,1:删除
     */
    private Byte status;

    /**
     * 创建ip
     */
    private String createIp;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 更新时间
     */
    private Long updateDate;

    /**
     * md
     */
    private String mdContent;

    private static final long serialVersionUID = 1L;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getRefArticleId() {
        return refArticleId;
    }

    public void setRefArticleId(String refArticleId) {
        this.refArticleId = refArticleId == null ? null : refArticleId.trim();
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId == null ? null : refUserId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public Integer getCountView() {
        return countView;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    public Integer getCountComment() {
        return countComment;
    }

    public void setCountComment(Integer countComment) {
        this.countComment = countComment;
    }

    public Integer getCountCollection() {
        return countCollection;
    }

    public void setCountCollection(Integer countCollection) {
        this.countCollection = countCollection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent == null ? null : mdContent.trim();
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
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getRefArticleId() == null ? other.getRefArticleId() == null : this.getRefArticleId().equals(other.getRefArticleId()))
            && (this.getRefUserId() == null ? other.getRefUserId() == null : this.getRefUserId().equals(other.getRefUserId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCoverImage() == null ? other.getCoverImage() == null : this.getCoverImage().equals(other.getCoverImage()))
            && (this.getCountView() == null ? other.getCountView() == null : this.getCountView().equals(other.getCountView()))
            && (this.getCountComment() == null ? other.getCountComment() == null : this.getCountComment().equals(other.getCountComment()))
            && (this.getCountCollection() == null ? other.getCountCollection() == null : this.getCountCollection().equals(other.getCountCollection()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateIp() == null ? other.getCreateIp() == null : this.getCreateIp().equals(other.getCreateIp()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getMdContent() == null ? other.getMdContent() == null : this.getMdContent().equals(other.getMdContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getRefArticleId() == null) ? 0 : getRefArticleId().hashCode());
        result = prime * result + ((getRefUserId() == null) ? 0 : getRefUserId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCoverImage() == null) ? 0 : getCoverImage().hashCode());
        result = prime * result + ((getCountView() == null) ? 0 : getCountView().hashCode());
        result = prime * result + ((getCountComment() == null) ? 0 : getCountComment().hashCode());
        result = prime * result + ((getCountCollection() == null) ? 0 : getCountCollection().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateIp() == null) ? 0 : getCreateIp().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getMdContent() == null) ? 0 : getMdContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", refArticleId=").append(refArticleId);
        sb.append(", refUserId=").append(refUserId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", content=").append(content);
        sb.append(", coverImage=").append(coverImage);
        sb.append(", countView=").append(countView);
        sb.append(", countComment=").append(countComment);
        sb.append(", countCollection=").append(countCollection);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", createIp=").append(createIp);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", mdContent=").append(mdContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}