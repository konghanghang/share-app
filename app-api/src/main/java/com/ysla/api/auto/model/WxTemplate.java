package com.ysla.api.auto.model;

import java.io.Serializable;

public class WxTemplate implements Serializable {
    private Integer templateId;

    private String wxTemplateId;

    private String first;

    private String keyNote;

    private String remark;

    private Integer createDate;

    private static final long serialVersionUID = 1L;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getWxTemplateId() {
        return wxTemplateId;
    }

    public void setWxTemplateId(String wxTemplateId) {
        this.wxTemplateId = wxTemplateId == null ? null : wxTemplateId.trim();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first == null ? null : first.trim();
    }

    public String getKeyNote() {
        return keyNote;
    }

    public void setKeyNote(String keyNote) {
        this.keyNote = keyNote == null ? null : keyNote.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
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
        WxTemplate other = (WxTemplate) that;
        return (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getWxTemplateId() == null ? other.getWxTemplateId() == null : this.getWxTemplateId().equals(other.getWxTemplateId()))
            && (this.getFirst() == null ? other.getFirst() == null : this.getFirst().equals(other.getFirst()))
            && (this.getKeyNote() == null ? other.getKeyNote() == null : this.getKeyNote().equals(other.getKeyNote()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getWxTemplateId() == null) ? 0 : getWxTemplateId().hashCode());
        result = prime * result + ((getFirst() == null) ? 0 : getFirst().hashCode());
        result = prime * result + ((getKeyNote() == null) ? 0 : getKeyNote().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", templateId=").append(templateId);
        sb.append(", wxTemplateId=").append(wxTemplateId);
        sb.append(", first=").append(first);
        sb.append(", keyNote=").append(keyNote);
        sb.append(", remark=").append(remark);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}