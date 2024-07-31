package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CommentMapper;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.service.ICommentService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-28
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param commentID 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Comment selectCommentByCommentID(Long commentID)
    {
        return commentMapper.selectCommentByCommentID(commentID);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param comment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param comment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param comment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param commentIDs 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentIDs(Long[] commentIDs)
    {
        return commentMapper.deleteCommentByCommentIDs(commentIDs);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param commentID 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCommentByCommentID(Long commentID)
    {
        return commentMapper.deleteCommentByCommentID(commentID);
    }
}
