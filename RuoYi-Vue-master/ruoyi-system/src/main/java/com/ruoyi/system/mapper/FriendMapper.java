package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Friend;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
public interface FriendMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param friendID 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Friend selectFriendByFriendID(Long friendID);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param friend 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Friend> selectFriendList(Friend friend);

    /**
     * 新增【请填写功能名称】
     * 
     * @param friend 【请填写功能名称】
     * @return 结果
     */
    public int insertFriend(Friend friend);

    /**
     * 修改【请填写功能名称】
     * 
     * @param friend 【请填写功能名称】
     * @return 结果
     */
    public int updateFriend(Friend friend);

    /**
     * 删除【请填写功能名称】
     * 
     * @param friendID 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFriendByFriendID(Long friendID);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param friendIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFriendByFriendIDs(Long[] friendIDs);
}
