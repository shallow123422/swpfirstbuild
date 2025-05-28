package com.ruoyi.paperspublish.mapper;

import java.util.List;
import com.ruoyi.paperspublish.domain.Paperspublish;

/**
 * 我的论文Mapper接口
 * 
 * @author ws
 * @date 2025-05-25
 */
public interface PaperspublishMapper 
{
    /**
     * 查询我的论文
     * 
     * @param papersId 我的论文主键
     * @return 我的论文
     */
    public Paperspublish selectPaperspublishByPapersId(Long papersId);

    /**
     * 查询我的论文列表
     * 
     * @param paperspublish 我的论文
     * @return 我的论文集合
     */
    public List<Paperspublish> selectPaperspublishList(Paperspublish paperspublish);

    /**
     * 新增我的论文
     * 
     * @param paperspublish 我的论文
     * @return 结果
     */
    public int insertPaperspublish(Paperspublish paperspublish);

    /**
     * 修改我的论文
     * 
     * @param paperspublish 我的论文
     * @return 结果
     */
    public int updatePaperspublish(Paperspublish paperspublish);

    /**
     * 删除我的论文
     * 
     * @param papersId 我的论文主键
     * @return 结果
     */
    public int deletePaperspublishByPapersId(Long papersId);

    /**
     * 批量删除我的论文
     * 
     * @param papersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaperspublishByPapersIds(Long[] papersIds);
}
