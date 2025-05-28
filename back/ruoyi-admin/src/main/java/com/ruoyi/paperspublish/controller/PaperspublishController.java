package com.ruoyi.paperspublish.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.paperspublish.domain.Paperspublish;
import com.ruoyi.paperspublish.service.IPaperspublishService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 我的论文Controller
 * 
 * @author ws
 * @date 2025-05-25
 */
@RestController
@RequestMapping("/paperspublish/paperspublish")
public class PaperspublishController extends BaseController
{
    @Autowired
    private IPaperspublishService paperspublishService;

    /**
     * 查询我的论文列表
     */
    @PreAuthorize("@ss.hasPermi('paperspublish:paperspublish:list')")
    @GetMapping("/list")
    public AjaxResult list(Paperspublish paperspublish)
    {
        List<Paperspublish> list = paperspublishService.selectPaperspublishList(paperspublish);
        return success(list);
    }

    /**
     * 导出我的论文列表
     */
    @PreAuthorize("@ss.hasPermi('paperspublish:paperspublish:export')")
    @Log(title = "我的论文", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Paperspublish paperspublish)
    {
        List<Paperspublish> list = paperspublishService.selectPaperspublishList(paperspublish);
        // ✅ 只保留 uploadTime 不为 null 的记录
        list = list.stream()
                .filter(item -> item.getUploadTime() != null)
                .collect(Collectors.toList());
        ExcelUtil<Paperspublish> util = new ExcelUtil<Paperspublish>(Paperspublish.class);
        util.exportExcel(response, list, "我的论文数据");
    }

    /**
     * 获取我的论文详细信息
     */
    @PreAuthorize("@ss.hasPermi('paperspublish:paperspublish:query')")
    @GetMapping(value = "/{papersId}")
    public AjaxResult getInfo(@PathVariable("papersId") Long papersId)
    {
        return success(paperspublishService.selectPaperspublishByPapersId(papersId));
    }

    /**
     * 新增我的论文
     */
    @PreAuthorize("@ss.hasPermi('paperspublish:paperspublish:add')")
    @Log(title = "我的论文", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Paperspublish paperspublish)
    {
        paperspublish.setUserId(getUserId());
        return toAjax(paperspublishService.insertPaperspublish(paperspublish));
    }

    /**
     * 修改我的论文
     */
    @PreAuthorize("@ss.hasPermi('paperspublish:paperspublish:edit')")
    @Log(title = "我的论文", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Paperspublish paperspublish)
    {
        return toAjax(paperspublishService.updatePaperspublish(paperspublish));
    }

    /**
     * 删除我的论文
     */
    @PreAuthorize("@ss.hasPermi('paperspublish:paperspublish:remove')")
    @Log(title = "我的论文", businessType = BusinessType.DELETE)
	@DeleteMapping("/{papersIds}")
    public AjaxResult remove(@PathVariable Long[] papersIds)
    {
        return toAjax(paperspublishService.deletePaperspublishByPapersIds(papersIds));
    }

}
