package com.ruoyi.papers.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.papersfound.domain.Papersfound;
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
import com.ruoyi.papers.domain.Papers;
import com.ruoyi.papers.service.IPapersService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 论文管理Controller
 * 
 * @author ws
 * @date 2025-05-25
 */
@RestController
@RequestMapping("/papers/papers")
public class PapersController extends BaseController
{
    @Autowired
    private IPapersService papersService;

    /**
     * 查询论文管理列表
     */
    @PreAuthorize("@ss.hasPermi('papers:papers:list')")
    @GetMapping("/list")
    public AjaxResult list(Papers papers)
    {
        List<Papers> list;
        if (papers.getPapersCategory() != null && !papers.getPapersCategory().isEmpty()) {
            // 如果指定了类别，查询该类别及其子节点
            list = papersService.selectPapersWithChildren(papers);
        } else {
            // 否则查询所有
            list = papersService.selectPapersList(papers);
        }
        return success(list);
    }

    /**
     * 导出论文管理列表
     */
    @PreAuthorize("@ss.hasPermi('papers:papers:export')")
    @Log(title = "论文管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, Papers papers)
    {
        List<Papers> list;
        if (papers.getPapersCategory() != null && !papers.getPapersCategory().isEmpty()) {
            // 如果指定了类别，导出该类别及其子节点的数据
            list = papersService.selectPapersWithChildren(papers);
        } else {
            // 否则导出查询条件过滤后的数据
            list = papersService.selectPapersList(papers);
        }
        // ✅ 只保留 uploadTime 不为 null 的记录
        list = list.stream()
                .filter(item -> item.getUploadTime() != null)
                .collect(Collectors.toList());
        ExcelUtil<Papers> util = new ExcelUtil<Papers>(Papers.class);
        util.exportExcel(response, list, "论文管理数据");
    }

    /**
     * 获取论文管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('papers:papers:query')")
    @GetMapping(value = "/{papersId}")
    public AjaxResult getInfo(@PathVariable("papersId") Long papersId)
    {
        return success(papersService.selectPapersByPapersId(papersId));
    }

    /**
     * 新增论文管理
     */
    @PreAuthorize("@ss.hasPermi('papers:papers:add')")
    @Log(title = "论文管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Papers papers)
    {
        return toAjax(papersService.insertPapers(papers));
    }

    /**
     * 修改论文管理
     */
    @PreAuthorize("@ss.hasPermi('papers:papers:edit')")
    @Log(title = "论文管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Papers papers)
    {
        return toAjax(papersService.updatePapers(papers));
    }

    /**
     * 删除论文管理
     */
    @PreAuthorize("@ss.hasPermi('papers:papers:remove')")
    @Log(title = "论文管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{papersIds}")
    public AjaxResult remove(@PathVariable Long[] papersIds)
    {
        return toAjax(papersService.deletePapersByPapersIds(papersIds));
    }
}
