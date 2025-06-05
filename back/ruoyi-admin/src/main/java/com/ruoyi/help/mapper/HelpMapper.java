package com.ruoyi.help.mapper;

import java.util.List;
import com.ruoyi.help.domain.Help;

/**
 * 辅助功能Mapper接口
 * 
 * @author ws
 * @date 2025-06-04
 */
public interface HelpMapper 
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
     * 删除辅助功能
     * 
     * @param papersId 辅助功能主键
     * @return 结果
     */
    public int deleteHelpByPapersId(Long papersId);

    /**
     * 批量删除辅助功能
     * 
     * @param papersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHelpByPapersIds(Long[] papersIds);
}
