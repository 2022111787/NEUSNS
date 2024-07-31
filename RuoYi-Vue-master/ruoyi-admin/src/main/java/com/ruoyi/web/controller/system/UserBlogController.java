package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.service.ICommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IBlogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
@RestController
@RequestMapping("/system/blog")
public class UserBlogController extends BaseController
{
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICommentService commentService;
    /**
     * 查询列表,只为用户提供审核通过的博文
     */
   // @PreAuthorize("@ss.hasPermi('system:blog:list')")
   // @GetMapping("/{")

    @GetMapping("/list")
       public TableDataInfo list (Blog blog)
        {
            startPage();
            long status=1;
            blog.setPostStatus(status);
            List<Blog> list = blogService.selectBlogList(blog);
            return getDataTable(list);
        }
        //  @PreAuthorize("@ss.hasPermi('system:blog:{postID}')")


    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:blog:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Blog blog)
    {
        List<Blog> list = blogService.selectBlogList(blog);
        ExcelUtil<Blog> util = new ExcelUtil<Blog>(Blog.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }




    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:blog:query')")
    @GetMapping(value = "/{postID}")
    public AjaxResult getInfo(@PathVariable("postID") Long postID)
    {
        return success(blogService.selectBlogByPostID(postID));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:blog:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Blog blog)
    {
        return toAjax(blogService.insertBlog(blog));
    }

    /**
     * 修改【请填写功能名称】
     */
    /**
    修改功能
     */
    @PreAuthorize("@ss.hasPermi('system:blog:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Blog blog)
    {
        return toAjax(blogService.updateBlog(blog));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:blog:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIDs}")
    public AjaxResult remove(@PathVariable Long[] postIDs)
    {
        return toAjax(blogService.deleteBlogByPostIDs(postIDs));
    }


    /**
     * 展示自己的博文
     *
     *
     *
     *
     */
    @GetMapping("/mylist/{userID}")
    public TableDataInfo mylist (Blog blog,@PathVariable("userID") Long userID)
    {

        blog.setUserID(userID);
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    /**
     * 展示博文的详情界面
     * @param postID
     * @return
     */
    @GetMapping(value = "/list/{postID}")
    public AjaxResult getdetailInfo(@PathVariable("postID") Long postID)
    {
        return success(blogService.selectBlogByPostID(postID));
    }


    /**
     * 为博文添加评论
     * @param comment
     * @return
     */

    @PostMapping("/list")
//        @PreAuthorize("@ss.hasPermi('system:blog:list:add')")
    public AjaxResult add (@RequestBody Comment comment)
    {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return toAjax(commentService.insertComment(comment));
    }

    /**
     * 展示博文的评论界面
     * @param comment
     * @return
     */
    @GetMapping(value = "/list/{postID}/commentlist")
    public TableDataInfo list(Comment comment,@PathVariable("postID") Long postID)
    {
        comment.setPostID(postID);
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }







}
