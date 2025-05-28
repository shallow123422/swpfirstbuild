package com.ruoyi.paperspublish.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.paperspublish.mapper.PaperspublishMapper;
import com.ruoyi.paperspublish.domain.Paperspublish;
import com.ruoyi.paperspublish.service.IPaperspublishService;

/**
 * 我的论文Service业务层处理
 * 
 * @author ws
 * @date 2025-05-25
 */
@Service
public class PaperspublishServiceImpl implements IPaperspublishService 
{
    @Autowired
    private PaperspublishMapper paperspublishMapper;

    /**
     * 查询我的论文
     * 
     * @param papersId 我的论文主键
     * @return 我的论文
     */
    @Override
    public Paperspublish selectPaperspublishByPapersId(Long papersId)
    {
        return paperspublishMapper.selectPaperspublishByPapersId(papersId);
    }

    /**
     * 查询我的论文列表
     * 
     * @param paperspublish 我的论文
     * @return 我的论文
     */
    @DataScope(userAlias = "u")
    @Override
    public List<Paperspublish> selectPaperspublishList(Paperspublish paperspublish)
    {
        return paperspublishMapper.selectPaperspublishList(paperspublish);
    }

    /**
     * 新增我的论文
     * 
     * @param paperspublish 我的论文
     * @return 结果
     */
    @Override
    public int insertPaperspublish(Paperspublish paperspublish)
    {
        return paperspublishMapper.insertPaperspublish(paperspublish);
    }

    /**
     * 修改我的论文
     * 
     * @param paperspublish 我的论文
     * @return 结果
     */
    @Override
    public int updatePaperspublish(Paperspublish paperspublish)
    {
        return paperspublishMapper.updatePaperspublish(paperspublish);
    }

    /**
     * 批量删除我的论文
     * 
     * @param papersIds 需要删除的我的论文主键
     * @return 结果
     */
    @Override
    public int deletePaperspublishByPapersIds(Long[] papersIds)
    {
        return paperspublishMapper.deletePaperspublishByPapersIds(papersIds);
    }

    /**
     * 删除我的论文信息
     * 
     * @param papersId 我的论文主键
     * @return 结果
     */
    @Override
    public int deletePaperspublishByPapersId(Long papersId)
    {
        return paperspublishMapper.deletePaperspublishByPapersId(papersId);
    }
}
