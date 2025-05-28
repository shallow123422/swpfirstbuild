package com.ruoyi.papersfound.controller;

import java.util.List;
import java.util.stream.Collectors;
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
import com.ruoyi.papersfound.domain.Papersfound;
import com.ruoyi.papersfound.service.IPapersfoundService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 论文查询Controller
 * 
 * @author ws
 * @date 2025-05-25
 */
@RestController
@RequestMapping("/papersfound/papersfound")
public class PapersfoundController extends BaseController
{
    @Autowired
    private IPapersfoundService papersfoundService;

    /**
     * 查询论文查询列表
     */
    @PreAuthorize("@ss.hasPermi('papersfound:papersfound:list')")
    @GetMapping("/list")
    public AjaxResult list(Papersfound papersfound)
    {
        List<Papersfound> list;
        if (papersfound.getPapersCategory() != null && !papersfound.getPapersCategory().isEmpty()) {
            // 如果指定了类别，查询该类别及其子节点
            list = papersfoundService.selectPapersfoundWithChildren(papersfound);
        } else {
            // 否则查询所有
            list = papersfoundService.selectPapersfoundList(papersfound);
        }
        return success(list);
    }

    /**
     * 导出论文查询列表
     */
    @PreAuthorize("@ss.hasPermi('papersfound:papersfound:export')")
    @Log(title = "论文查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Papersfound papersfound)
    {
        List<Papersfound> list = papersfoundService.selectPapersfoundList(papersfound);
        // ✅ 只保留 uploadTime 不为 null 的记录
        list = list.stream()
                .filter(item -> item.getUploadTime() != null)
                .collect(Collectors.toList());
        ExcelUtil<Papersfound> util = new ExcelUtil<Papersfound>(Papersfound.class);
        util.exportExcel(response, list, "论文查询数据");
    }

    /**
     * 获取论文查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('papersfound:papersfound:query')")
    @GetMapping(value = "/{papersId}")
    public AjaxResult getInfo(@PathVariable("papersId") Long papersId)
    {
        return success(papersfoundService.selectPapersfoundByPapersId(papersId));
    }

    /**
     * 新增论文查询
     */
    @PreAuthorize("@ss.hasPermi('papersfound:papersfound:add')")
    @Log(title = "论文查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Papersfound papersfound)
    {
        papersfound.setUserId(getUserId());
        return toAjax(papersfoundService.insertPapersfound(papersfound));
    }

    /**
     * 修改论文查询
     */
    @PreAuthorize("@ss.hasPermi('papersfound:papersfound:edit')")
    @Log(title = "论文查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Papersfound papersfound)
    {
        return toAjax(papersfoundService.updatePapersfound(papersfound));
    }

    /**
     * 删除论文查询
     */
    @PreAuthorize("@ss.hasPermi('papersfound:papersfound:remove')")
    @Log(title = "论文查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{papersIds}")
    public AjaxResult remove(@PathVariable Long[] papersIds)
    {
        return toAjax(papersfoundService.deletePapersfoundByPapersIds(papersIds));
    }
}
