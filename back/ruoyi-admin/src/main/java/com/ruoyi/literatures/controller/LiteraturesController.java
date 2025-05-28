package com.ruoyi.literatures.controller;

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
import com.ruoyi.literatures.domain.Literatures;
import com.ruoyi.literatures.service.ILiteraturesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import java.util.List;
import com.ruoyi.literatures.domain.Literatures;
/**
 * 参考文献Controller
 *
 * @author ws
 * @date 2025-05-26
 */
@RestController
@RequestMapping("/literatures/literatures")
public class LiteraturesController extends BaseController
{
    @Autowired
    private ILiteraturesService literaturesService;

    /**
     * 查询参考文献列表
     *
     */
    /**
     * 查询参考文献列表
     */
    @PreAuthorize("@ss.hasPermi('literatures:literatures:list')")
    @GetMapping("/list")
    public AjaxResult list(Literatures literatures)
    {
        List<Literatures> list;
        if (literatures.getLiteraturesCategory() != null && !literatures.getLiteraturesCategory().isEmpty()) {
            // 如果指定了类别，查询该类别及其子节点
            list = literaturesService.selectLiteraturesWithChildren(literatures);
        } else {
            // 否则查询所有
            list = literaturesService.selectLiteraturesList(literatures);
        }
        return success(list);
    }

    /**
     * 导出参考文献列表
     */
    @PreAuthorize("@ss.hasPermi('literatures:literatures:export')")
    @Log(title = "参考文献", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Literatures literatures)
    {
        List<Literatures> list = literaturesService.selectLiteraturesList(literatures);
        // ✅ 只保留 uploadTime 不为 null 的记录
        list = list.stream()
                .filter(item -> item.getUploadTime() != null)
                .collect(Collectors.toList());
        ExcelUtil<Literatures> util = new ExcelUtil<Literatures>(Literatures.class);
        util.exportExcel(response, list, "参考文献数据");
    }

    /**
     * 获取参考文献详细信息
     */
    @PreAuthorize("@ss.hasPermi('literatures:literatures:query')")
    @GetMapping(value = "/{literaturesId}")
    public AjaxResult getInfo(@PathVariable("literaturesId") Long literaturesId)
    {
        return success(literaturesService.selectLiteraturesByLiteraturesId(literaturesId));
    }

    /**
     * 新增参考文献
     */
    @PreAuthorize("@ss.hasPermi('literatures:literatures:add')")
    @Log(title = "参考文献", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Literatures literatures)
    {
        literatures.setUserId(getUserId());
        return toAjax(literaturesService.insertLiteratures(literatures));
    }

    /**
     * 修改参考文献
     */
    @PreAuthorize("@ss.hasPermi('literatures:literatures:edit')")
    @Log(title = "参考文献", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Literatures literatures)
    {
        return toAjax(literaturesService.updateLiteratures(literatures));
    }

    /**
     * 删除参考文献
     */
    @PreAuthorize("@ss.hasPermi('literatures:literatures:remove')")
    @Log(title = "参考文献", businessType = BusinessType.DELETE)
	@DeleteMapping("/{literaturesIds}")
    public AjaxResult remove(@PathVariable Long[] literaturesIds)
    {
        return toAjax(literaturesService.deleteLiteraturesByLiteraturesIds(literaturesIds));
    }
}
