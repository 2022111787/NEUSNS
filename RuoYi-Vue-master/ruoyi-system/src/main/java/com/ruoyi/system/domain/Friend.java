package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 friend
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
public class Friend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long friendID;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String friendName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String avatar;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userID;

    public void setFriendID(Long friendID) 
    {
        this.friendID = friendID;
    }

    public Long getFriendID() 
    {
        return friendID;
    }
    public void setFriendName(String friendName) 
    {
        this.friendName = friendName;
    }

    public String getFriendName() 
    {
        return friendName;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setUserID(Long userID) 
    {
        this.userID = userID;
    }

    public Long getUserID() 
    {
        return userID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("friendID", getFriendID())
            .append("friendName", getFriendName())
            .append("avatar", getAvatar())
            .append("userID", getUserID())
            .toString();
    }
}
