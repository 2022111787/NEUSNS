package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FriendMapper;
import com.ruoyi.system.domain.Friend;
import com.ruoyi.system.service.IFriendService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
@Service
public class FriendServiceImpl implements IFriendService 
{
    @Autowired
    private FriendMapper friendMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param friendID 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Friend selectFriendByFriendID(Long friendID)
    {
        return friendMapper.selectFriendByFriendID(friendID);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param friend 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Friend> selectFriendList(Friend friend)
    {
        return friendMapper.selectFriendList(friend);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param friend 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFriend(Friend friend)
    {
        return friendMapper.insertFriend(friend);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param friend 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFriend(Friend friend)
    {
        return friendMapper.updateFriend(friend);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param friendIDs 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFriendByFriendIDs(Long[] friendIDs)
    {
        return friendMapper.deleteFriendByFriendIDs(friendIDs);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param friendID 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFriendByFriendID(Long friendID)
    {
        return friendMapper.deleteFriendByFriendID(friendID);
    }
}
