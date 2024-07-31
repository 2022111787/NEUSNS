package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 comment
 *
 * @author ruoyi
 * @date 2024-07-29
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long commentID;

    /**  */
    @Excel(name = "")
    private Long postID;

    /**  */
    @Excel(name = "")
    private String commentAuthor;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commentDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String commentContent;

    public void setCommentID(Long commentID)
    {
        this.commentID = commentID;
    }

    public Long getCommentID()
    {
        return commentID;
    }
    public void setPostID(Long postID)
    {
        this.postID = postID;
    }

    public Long getPostID()
    {
        return postID;
    }
    public void setCommentAuthor(String commentAuthor)
    {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentAuthor()
    {
        return commentAuthor;
    }
    public void setCommentDate(Date commentDate)
    {
        this.commentDate = commentDate;
    }

    public Date getCommentDate()
    {
        return commentDate;
    }
    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

    public String getCommentContent()
    {
        return commentContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("commentID", getCommentID())
                .append("postID", getPostID())
                .append("commentAuthor", getCommentAuthor())
                .append("commentDate", getCommentDate())
                .append("commentContent", getCommentContent())
                .toString();
    }
}
