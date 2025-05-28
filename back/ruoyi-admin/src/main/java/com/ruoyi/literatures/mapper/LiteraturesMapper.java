package com.ruoyi.literatures.mapper;

import java.util.List;
import com.ruoyi.literatures.domain.Literatures;

/**
 * 参考文献Mapper接口
 * 
 * @author ws
 * @date 2025-05-26
 */
public interface LiteraturesMapper 
{
    /**
     * 查询参考文献
     * 
     * @param literaturesId 参考文献主键
     * @return 参考文献
     */
    public Literatures selectLiteraturesByLiteraturesId(Long literaturesId);

    /**
     * 查询参考文献列表
     * 
     * @param literatures 参考文献
     * @return 参考文献集合
     */
    public List<Literatures> selectLiteraturesList(Literatures literatures);

    /**
     * 新增参考文献
     * 
     * @param literatures 参考文献
     * @return 结果
     */
    public int insertLiteratures(Literatures literatures);

    /**
     * 修改参考文献
     * 
     * @param literatures 参考文献
     * @return 结果
     */
    public int updateLiteratures(Literatures literatures);

    /**
     * 删除参考文献
     * 
     * @param literaturesId 参考文献主键
     * @return 结果
     */
    public int deleteLiteraturesByLiteraturesId(Long literaturesId);

    /**
     * 批量删除参考文献
     * 
     * @param literaturesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLiteraturesByLiteraturesIds(Long[] literaturesIds);

    /**
     * 查询指定类别及其所有子节点的文献
     *
     * @param literatures 文献类别
     * @return 文献列表
     */
    public List<Literatures> selectLiteraturesWithChildren(Literatures literatures);
}
