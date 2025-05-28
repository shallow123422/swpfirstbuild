package com.ruoyi.papers.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.papers.mapper.PapersMapper;
import com.ruoyi.papers.domain.Papers;
import com.ruoyi.papers.service.IPapersService;

/**
 * 论文管理Service业务层处理
 * 
 * @author ws
 * @date 2025-05-25
 */
@Service
public class PapersServiceImpl implements IPapersService 
{
    @Autowired
    private PapersMapper papersMapper;

    /**
     * 查询论文管理
     * 
     * @param papersId 论文管理主键
     * @return 论文管理
     */
    @Override
    public Papers selectPapersByPapersId(Long papersId)
    {
        return papersMapper.selectPapersByPapersId(papersId);
    }

    /**
     * 查询论文管理列表
     * 
     * @param papers 论文管理
     * @return 论文管理
     */
    @Override
    public List<Papers> selectPapersList(Papers papers)
    {
        return papersMapper.selectPapersList(papers);
    }

    /**
     * 新增论文管理
     * 
     * @param papers 论文管理
     * @return 结果
     */
    @Override
    public int insertPapers(Papers papers)
    {
        return papersMapper.insertPapers(papers);
    }

    /**
     * 修改论文管理
     * 
     * @param papers 论文管理
     * @return 结果
     */
    @Override
    public int updatePapers(Papers papers)
    {
        return papersMapper.updatePapers(papers);
    }

    /**
     * 批量删除论文管理
     * 
     * @param papersIds 需要删除的论文管理主键
     * @return 结果
     */
    @Override
    public int deletePapersByPapersIds(Long[] papersIds)
    {
        return papersMapper.deletePapersByPapersIds(papersIds);
    }

    /**
     * 删除论文管理信息
     * 
     * @param papersId 论文管理主键
     * @return 结果
     */
    @Override
    public int deletePapersByPapersId(Long papersId)
    {
        return papersMapper.deletePapersByPapersId(papersId);
    }

    /**
     * 查询指定类别及其所有子节点的论文
     *
     * @param papers 论文查询（包含类别）
     * @return 论文列表
     */
    @Override
    public List<Papers> selectPapersWithChildren(Papers papers)
    {
        return papersMapper.selectPapersWithChildren(papers);
    }
}
