package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BlogMapper;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.service.IBlogService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
@Service
public class BlogServiceImpl implements IBlogService 
{
    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param postID 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Blog selectBlogByPostID(Long postID)
    {
        return blogMapper.selectBlogByPostID(postID);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param blog 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Blog> selectBlogList(Blog blog)
    {
        return blogMapper.selectBlogList(blog);
    }
    @Override
    public Blog selectBlogListdetail(Blog blog){return blogMapper.selectBlogListdetail(blog);}
    /**
     * 新增【请填写功能名称】
     * 
     * @param blog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBlog(Blog blog)
    {
        return blogMapper.insertBlog(blog);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param blog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBlog(Blog blog)
    {
        return blogMapper.updateBlog(blog);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param postIDs 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBlogByPostIDs(Long[] postIDs)
    {
        return blogMapper.deleteBlogByPostIDs(postIDs);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param postID 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBlogByPostID(Long postID)
    {
        return blogMapper.deleteBlogByPostID(postID);
    }
    @Override
    public List<Blog> selectmyBlogList(Blog blog, Long userID){return blogMapper.selectmyBlogList(blog,userID);}
}
