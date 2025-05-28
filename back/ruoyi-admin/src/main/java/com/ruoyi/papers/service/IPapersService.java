package com.ruoyi.papers.service;

import java.util.List;
import com.ruoyi.papers.domain.Papers;

/**
 * 论文管理Service接口
 * 
 * @author ws
 * @date 2025-05-25
 */
public interface IPapersService 
{
    /**
     * 查询论文管理
     * 
     * @param papersId 论文管理主键
     * @return 论文管理
     */
    public Papers selectPapersByPapersId(Long papersId);

    /**
     * 查询论文管理列表
     * 
     * @param papers 论文管理
     * @return 论文管理集合
     */
    public List<Papers> selectPapersList(Papers papers);

    /**
     * 新增论文管理
     * 
     * @param papers 论文管理
     * @return 结果
     */
    public int insertPapers(Papers papers);

    /**
     * 修改论文管理
     * 
     * @param papers 论文管理
     * @return 结果
     */
    public int updatePapers(Papers papers);

    /**
     * 批量删除论文管理
     * 
     * @param papersIds 需要删除的论文管理主键集合
     * @return 结果
     */
    public int deletePapersByPapersIds(Long[] papersIds);

    /**
     * 删除论文管理信息
     * 
     * @param papersId 论文管理主键
     * @return 结果
     */
    public int deletePapersByPapersId(Long papersId);

    /**
     * 查询指定类别及其所有子节点的论文
     *
     * @param papers 论文查询（包含类别）
     * @return 论文列表
     */
    public List<Papers> selectPapersWithChildren(Papers papers);
}
