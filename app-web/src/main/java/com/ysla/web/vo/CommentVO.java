package com.ysla.web.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 评论vo
 * @author konghang
 */
public class CommentVO {

    @NotEmpty(message = "评论内容不能为空")
    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String content;

    @NotEmpty(message = "文章id不能为空")
    @Getter
    @Setter
    private String articleId;

    @NotEmpty(message = "回复某条评论的id不能为空")
    @Getter
    @Setter
    private String replayRefId;

    @Getter
    @Setter
    private String replayTo = "";

    @Override
    public String toString() {
        return "CommentVO{" +
                "content='" + content + '\'' +
                ", articleId='" + articleId + '\'' +
                ", replayRefId='" + replayRefId + '\'' +
                ", replayTo='" + replayTo + '\'' +
                '}';
    }
}
