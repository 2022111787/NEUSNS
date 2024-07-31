package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.system.domain.Friend;
import com.ruoyi.system.service.IFriendService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
@RestController
@RequestMapping("/system/friend")
public class FriendController extends BaseController
{
    @Autowired
    private IFriendService friendService;

    /**
     * 查询列表,USERID和自己相同的
     */
    @PreAuthorize("@ss.hasPermi('system:friend:list')")
    @GetMapping("/list")
    public TableDataInfo list(Friend friend)
    {
        startPage();
        List<Friend> list = friendService.selectFriendList(friend);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:friend:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Friend friend)
    {
        List<Friend> list = friendService.selectFriendList(friend);
        ExcelUtil<Friend> util = new ExcelUtil<Friend>(Friend.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:friend:query')")
    @GetMapping(value = "/{friendID}")
    public AjaxResult getInfo(@PathVariable("friendID") Long friendID)
    {
        return success(friendService.selectFriendByFriendID(friendID));
    }

    /**
     * 新增【请填写功能名称】
     */


    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:friend:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Friend friend)
    {
        return toAjax(friendService.updateFriend(friend));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:friend:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{friendIDs}")
    public AjaxResult remove(@PathVariable Long[] friendIDs)
    {
        return toAjax(friendService.deleteFriendByFriendIDs(friendIDs));
    }
}
