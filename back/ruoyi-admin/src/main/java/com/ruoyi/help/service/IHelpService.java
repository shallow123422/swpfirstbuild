package com.ruoyi.help.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.help.domain.Help;

/**
 * 辅助功能Service接口
 * 
 * @author ws
 * @date 2025-06-04
 */
public interface IHelpService 
{
    /**
     * 查询辅助功能
     * 
     * @param papersId 辅助功能主键
     * @return 辅助功能
     */
    public Help selectHelpByPapersId(Long papersId);

    /**
     * 查询辅助功能列表
     * 
     * @param help 辅助功能
     * @return 辅助功能集合
     */
    public List<Help> selectHelpList(Help help);

    /**
     * 新增辅助功能
     * 
     * @param help 辅助功能
     * @return 结果
     */
    public int insertHelp(Help help);

    /**
     * 修改辅助功能
     * 
     * @param help 辅助功能
     * @return 结果
     */
    public int updateHelp(Help help);

    /**
     * 批量删除辅助功能
     * 
     * @param papersIds 需要删除的辅助功能主键集合
     * @return 结果
     */
    public int deleteHelpByPapersIds(Long[] papersIds);

    /**
     * 删除辅助功能信息
     * 
     * @param papersId 辅助功能主键
     * @return 结果
     */
    public int deleteHelpByPapersId(Long papersId);

    // IHelpService.java 中添加以下方法：
    /**
     * 获取作者合作关系
     * @param authorName 作者名
     * @return 合作关系列表
     */
    public List<Map<String, Object>> getCoAuthors(String authorName);

    /**
     * 计算作者的工作量分数
     * @param authorName 作者名
     * @return 分数统计结果
     */
    public Map<String, Object> calculateWorkload(String authorName);
}
