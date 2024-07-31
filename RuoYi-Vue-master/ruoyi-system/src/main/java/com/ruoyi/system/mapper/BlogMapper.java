package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Blog;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
public interface BlogMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param postID 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Blog selectBlogByPostID(Long postID);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param blog 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Blog> selectBlogList(Blog blog);

    /**
     * 新增【请填写功能名称】
     * 
     * @param blog 【请填写功能名称】
     * @return 结果
     */
    public int insertBlog(Blog blog);

    /**
     * 修改【请填写功能名称】
     * 
     * @param blog 【请填写功能名称】
     * @return 结果
     */
    public int updateBlog(Blog blog);

    /**
     * 删除【请填写功能名称】
     * 
     * @param postID 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBlogByPostID(Long postID);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param postIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogByPostIDs(Long[] postIDs);

   public Blog selectBlogListdetail(Blog blog);

  public  List<Blog> selectmyBlogList(Blog blog, Long userID);
}
