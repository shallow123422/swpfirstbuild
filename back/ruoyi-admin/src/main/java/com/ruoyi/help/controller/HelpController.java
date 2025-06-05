package com.ruoyi.help.controller;

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
import com.ruoyi.help.domain.Help;
import com.ruoyi.help.service.IHelpService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 辅助功能Controller
 * 
 * @author ws
 * @date 2025-06-04
 */
@RestController
@RequestMapping("/help/help")
public class HelpController extends BaseController
{
    @Autowired
    private IHelpService helpService;

    /**
     * 查询辅助功能列表
     */
    @PreAuthorize("@ss.hasPermi('help:help:list')")
    @GetMapping("/list")
    public AjaxResult list(Help help)
    {
        List<Help> list = helpService.selectHelpList(help);
        return success(list);
    }

    /**
     * 导出辅助功能列表
     */
    @PreAuthorize("@ss.hasPermi('help:help:export')")
    @Log(title = "辅助功能", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Help help)
    {
        List<Help> list = helpService.selectHelpList(help);
        ExcelUtil<Help> util = new ExcelUtil<Help>(Help.class);
        util.exportExcel(response, list, "辅助功能数据");
    }

    /**
     * 获取辅助功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('help:help:query')")
    @GetMapping(value = "/{papersId}")
    public AjaxResult getInfo(@PathVariable("papersId") Long papersId)
    {
        return success(helpService.selectHelpByPapersId(papersId));
    }

    /**
     * 新增辅助功能
     */
    @PreAuthorize("@ss.hasPermi('help:help:add')")
    @Log(title = "辅助功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Help help)
    {
        return toAjax(helpService.insertHelp(help));
    }

    /**
     * 修改辅助功能
     */
    @PreAuthorize("@ss.hasPermi('help:help:edit')")
    @Log(title = "辅助功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Help help)
    {
        return toAjax(helpService.updateHelp(help));
    }

    /**
     * 删除辅助功能
     */
    @PreAuthorize("@ss.hasPermi('help:help:remove')")
    @Log(title = "辅助功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{papersIds}")
    public AjaxResult remove(@PathVariable Long[] papersIds)
    {
        return toAjax(helpService.deleteHelpByPapersIds(papersIds));
    }

    // HelpController.java 中添加：
    /**
     * 获取作者合作关系
     */
    @GetMapping("/coAuthors/{authorName}")
    public AjaxResult getCoAuthors(@PathVariable("authorName") String authorName)
    {
        return success(helpService.getCoAuthors(authorName));
    }

    /**
     * 获取作者工作量分数
     */
    @GetMapping("/workload/{authorName}")
    public AjaxResult getWorkload(@PathVariable("authorName") String authorName)
    {
        return success(helpService.calculateWorkload(authorName));
    }
}
