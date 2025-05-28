package com.ruoyi.papersfound.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.papersfound.mapper.PapersfoundMapper;
import com.ruoyi.papersfound.domain.Papersfound;
import com.ruoyi.papersfound.service.IPapersfoundService;

/**
 * 论文查询Service业务层处理
 * 
 * @author ws
 * @date 2025-05-25
 */
@Service
public class PapersfoundServiceImpl implements IPapersfoundService 
{
    @Autowired
    private PapersfoundMapper papersfoundMapper;

    /**
     * 查询论文查询
     * 
     * @param papersId 论文查询主键
     * @return 论文查询
     */
    @Override
    public Papersfound selectPapersfoundByPapersId(Long papersId)
    {
        return papersfoundMapper.selectPapersfoundByPapersId(papersId);
    }

    /**
     * 查询论文查询列表
     * 
     * @param papersfound 论文查询
     * @return 论文查询
     */
    @Override
    public List<Papersfound> selectPapersfoundList(Papersfound papersfound)
    {
        return papersfoundMapper.selectPapersfoundList(papersfound);
    }

    /**
     * 新增论文查询
     * 
     * @param papersfound 论文查询
     * @return 结果
     */
    @Override
    public int insertPapersfound(Papersfound papersfound)
    {
        return papersfoundMapper.insertPapersfound(papersfound);
    }

    /**
     * 修改论文查询
     * 
     * @param papersfound 论文查询
     * @return 结果
     */
    @Override
    public int updatePapersfound(Papersfound papersfound)
    {
        return papersfoundMapper.updatePapersfound(papersfound);
    }

    /**
     * 批量删除论文查询
     * 
     * @param papersIds 需要删除的论文查询主键
     * @return 结果
     */
    @Override
    public int deletePapersfoundByPapersIds(Long[] papersIds)
    {
        return papersfoundMapper.deletePapersfoundByPapersIds(papersIds);
    }

    /**
     * 删除论文查询信息
     * 
     * @param papersId 论文查询主键
     * @return 结果
     */
    @Override
    public int deletePapersfoundByPapersId(Long papersId)
    {
        return papersfoundMapper.deletePapersfoundByPapersId(papersId);
    }

    /**
     * 查询指定类别及其所有子节点的论文
     *
     * @param papersfound 论文查询（包含类别）
     * @return 论文列表
     */
    @Override
    public List<Papersfound> selectPapersfoundWithChildren(Papersfound papersfound)
    {
        return papersfoundMapper.selectPapersfoundWithChildren(papersfound);
    }
}
