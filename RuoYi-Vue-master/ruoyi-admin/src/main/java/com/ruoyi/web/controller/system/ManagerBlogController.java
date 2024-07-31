package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Blog;
import com.ruoyi.system.domain.Comment;
import com.ruoyi.system.service.IBlogService;
import com.ruoyi.system.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("api/admin/managerPost")
        public class ManagerBlogController extends BaseController
{

        @Autowired
        private IBlogService blogService;
        @Autowired
        private ICommentService commentService;
        /**
         * 查询未审核的博文内容
         */
        @GetMapping("/managerUnreviewedPost")
        public TableDataInfo UnreviewedPostList (Blog blog)
        {
                startPage();
                long status=0;
                blog.setPostStatus(status);
                List<Blog> list = blogService.selectBlogList(blog);
                return getDataTable(list);
        }

        /**
         * 审核功能
         */
        @PreAuthorize("@ss.hasPermi('system:blog:edit')")
        @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
        @PutMapping("/managerUnreviewedPost")
        public AjaxResult edit(@RequestBody Blog blog)
        {
                return toAjax(blogService.updateBlog(blog));
        }

        /**
         * 删除未审核的博文
         */
        @PreAuthorize("@ss.hasPermi('system:blog:remove')")
        @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
        @DeleteMapping("/managerUnreviewedPost/{postIDs}")
        public AjaxResult removeUnreiwed(@PathVariable Long[] postIDs)
        {
                return toAjax(blogService.deleteBlogByPostIDs(postIDs));
        }

        /**
         * 展示未审核博文的详情界面
         * @param postID
         * @return
         */
        @GetMapping(value = "/managerUnreviewedPost/{postID}")
        public AjaxResult getUnreviweddetailInfo(@PathVariable("postID") Long postID)
        {
                return success(blogService.selectBlogByPostID(postID));
        }
        /**
         * 展示未审核的博文的评论界面
         * @param comment
         * @return
         */
        @GetMapping(value = "/managerUnreviewedPost/{postID}/commentlist")
        public TableDataInfo Unreviewedlist(Comment comment,@PathVariable("postID") Long postID)
        {
                comment.setPostID(postID);
                List<Comment> list = commentService.selectCommentList(comment);
                return getDataTable(list);
        }

        /**
         * 查询已审核的博文内容
         */
        @GetMapping("/managerReviewedPost")
        public TableDataInfo ReviewedPostList (Blog blog)
        {
                startPage();
                long status=1;
                blog.setPostStatus(status);
                List<Blog> list = blogService.selectBlogList(blog);
                return getDataTable(list);
        }
        /**
         * 删除已审核的博文
         */
        @PreAuthorize("@ss.hasPermi('system:blog:remove')")
        @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
        @DeleteMapping("/managerReviewedPost/{postIDs}")
        public AjaxResult remove(@PathVariable Long[] postIDs)
        {
                return toAjax(blogService.deleteBlogByPostIDs(postIDs));
        }

        /**
         * 展示已审核博文的详情界面
         * @param postID
         * @return
         */
        @GetMapping(value = "/managerReviewedPost/{postID}")
        public AjaxResult getReviweddetailInfo(@PathVariable("postID") Long postID)
        {
                return success(blogService.selectBlogByPostID(postID));
        }
        /**
         * 展示博文的评论界面
         * @param comment
         * @return
         */
        @GetMapping(value = "/managerReviewedPost/{postID}/commentlist")
        public TableDataInfo list(Comment comment,@PathVariable("postID") Long postID)
        {
                comment.setPostID(postID);
                List<Comment> list = commentService.selectCommentList(comment);
                return getDataTable(list);
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










}
