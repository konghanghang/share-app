package com.ysla.web.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 文章vo
 * @author konghang
 */
public class ArticleVO {

    @Getter @Setter private String articleId;

    @NotEmpty(message = "content不能为空")
    @Getter @Setter private String content;

    @NotEmpty(message = "content不能为空")
    @Getter @Setter private String mdContent;

    @NotBlank(message = "title不能为空")
    @Length(max = 100,message = "title长度不能超过100")
    @Getter @Setter private String title;

    @NotNull(message = "类型不能为空")
    @Getter @Setter private String type;

    @NotBlank(message = "简介不能为空")
    @Getter @Setter private String description;

    //@NotEmpty(message = "封面图片不能为空")
    @Getter @Setter private String coverImage = "111";

    @Override
    public String toString() {
        return "ArticleVO{" +
                "articleId='" + articleId + '\'' +
                ", content='" + content + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
