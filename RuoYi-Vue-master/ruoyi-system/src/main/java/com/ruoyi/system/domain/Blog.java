package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 blog
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
public class Blog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long postID;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String postTitle;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String postAuthor;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date postDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long postStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userID;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String postContent;

    public void setPostID(Long postID) 
    {
        this.postID = postID;
    }

    public Long getPostID() 
    {
        return postID;
    }
    public void setPostTitle(String postTitle) 
    {
        this.postTitle = postTitle;
    }

    public String getPostTitle() 
    {
        return postTitle;
    }
    public void setPostAuthor(String postAuthor) 
    {
        this.postAuthor = postAuthor;
    }

    public String getPostAuthor() 
    {
        return postAuthor;
    }
    public void setPostDate(Date postDate) 
    {
        this.postDate = postDate;
    }

    public Date getPostDate() 
    {
        return postDate;
    }
    public void setPostStatus(Long postStatus) 
    {
        this.postStatus = postStatus;
    }

    public Long getPostStatus() 
    {
        return postStatus;
    }
    public void setUserID(Long userID) 
    {
        this.userID = userID;
    }

    public Long getUserID() 
    {
        return userID;
    }
    public void setPostContent(String postContent) 
    {
        this.postContent = postContent;
    }

    public String getPostContent() 
    {
        return postContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postID", getPostID())
            .append("postTitle", getPostTitle())
            .append("postAuthor", getPostAuthor())
            .append("postDate", getPostDate())
            .append("postStatus", getPostStatus())
            .append("userID", getUserID())
            .append("postContent", getPostContent())
            .toString();
    }
}
