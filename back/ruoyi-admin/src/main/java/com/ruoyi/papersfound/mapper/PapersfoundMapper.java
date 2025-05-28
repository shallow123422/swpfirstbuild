package com.ruoyi.papersfound.mapper;

import java.util.List;
import com.ruoyi.papersfound.domain.Papersfound;

/**
 * 论文查询Mapper接口
 * 
 * @author ws
 * @date 2025-05-25
 */
public interface PapersfoundMapper 
{
    /**
     * 查询论文查询
     * 
     * @param papersId 论文查询主键
     * @return 论文查询
     */
    public Papersfound selectPapersfoundByPapersId(Long papersId);

    /**
     * 查询论文查询列表
     * 
     * @param papersfound 论文查询
     * @return 论文查询集合
     */
    public List<Papersfound> selectPapersfoundList(Papersfound papersfound);

    /**
     * 新增论文查询
     * 
     * @param papersfound 论文查询
     * @return 结果
     */
    public int insertPapersfound(Papersfound papersfound);

    /**
     * 修改论文查询
     * 
     * @param papersfound 论文查询
     * @return 结果
     */
    public int updatePapersfound(Papersfound papersfound);

    /**
     * 删除论文查询
     * 
     * @param papersId 论文查询主键
     * @return 结果
     */
    public int deletePapersfoundByPapersId(Long papersId);

    /**
     * 批量删除论文查询
     * 
     * @param papersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePapersfoundByPapersIds(Long[] papersIds);

    /**
     * 查询指定类别及其所有子节点的论文
     *
     * @param papersfound 论文查询（包含类别）
     * @return 论文列表
     */
    public List<Papersfound> selectPapersfoundWithChildren(Papersfound papersfound);
}
