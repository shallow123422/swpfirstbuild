package com.ruoyi.literatures.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.literatures.mapper.LiteraturesMapper;
import com.ruoyi.literatures.domain.Literatures;
import com.ruoyi.literatures.service.ILiteraturesService;

/**
 * 参考文献Service业务层处理
 * 
 * @author ws
 * @date 2025-05-26
 */
@Service
public class LiteraturesServiceImpl implements ILiteraturesService 
{
    @Autowired
    private LiteraturesMapper literaturesMapper;

    /**
     * 查询参考文献
     * 
     * @param literaturesId 参考文献主键
     * @return 参考文献
     */
    @Override
    public Literatures selectLiteraturesByLiteraturesId(Long literaturesId)
    {
        return literaturesMapper.selectLiteraturesByLiteraturesId(literaturesId);
    }

    /**
     * 查询参考文献列表
     * 
     * @param literatures 参考文献
     * @return 参考文献
     */
    @DataScope(userAlias = "u")
    @Override
    public List<Literatures> selectLiteraturesList(Literatures literatures)
    {
        return literaturesMapper.selectLiteraturesList(literatures);
    }

    /**
     * 新增参考文献
     * 
     * @param literatures 参考文献
     * @return 结果
     */
    @Override
    public int insertLiteratures(Literatures literatures)
    {
        return literaturesMapper.insertLiteratures(literatures);
    }

    /**
     * 修改参考文献
     * 
     * @param literatures 参考文献
     * @return 结果
     */
    @Override
    public int updateLiteratures(Literatures literatures)
    {
        return literaturesMapper.updateLiteratures(literatures);
    }

    /**
     * 批量删除参考文献
     * 
     * @param literaturesIds 需要删除的参考文献主键
     * @return 结果
     */
    @Override
    public int deleteLiteraturesByLiteraturesIds(Long[] literaturesIds)
    {
        return literaturesMapper.deleteLiteraturesByLiteraturesIds(literaturesIds);
    }

    /**
     * 删除参考文献信息
     * 
     * @param literaturesId 参考文献主键
     * @return 结果
     */
    @Override
    public int deleteLiteraturesByLiteraturesId(Long literaturesId)
    {
        return literaturesMapper.deleteLiteraturesByLiteraturesId(literaturesId);
    }

    /**
     * 查询指定类别及其所有子节点的文献
     *
     * @param literatures 参考文献信息（包含类别）
     * @return 文献列表
     */
    @Override
    @DataScope(userAlias = "u")
    public List<Literatures> selectLiteraturesWithChildren(Literatures literatures)
    {
        return literaturesMapper.selectLiteraturesWithChildren(literatures);
    }
}
