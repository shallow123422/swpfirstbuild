package com.ruoyi.literatures.service;

import java.util.List;
import com.ruoyi.literatures.domain.Literatures;

/**
 * 参考文献Service接口
 * 
 * @author ws
 * @date 2025-05-26
 */
public interface ILiteraturesService 
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
     * 批量删除参考文献
     * 
     * @param literaturesIds 需要删除的参考文献主键集合
     * @return 结果
     */
    public int deleteLiteraturesByLiteraturesIds(Long[] literaturesIds);

    /**
     * 删除参考文献信息
     * 
     * @param literaturesId 参考文献主键
     * @return 结果
     */
    public int deleteLiteraturesByLiteraturesId(Long literaturesId);

    /**
     * 查询指定类别及其所有子节点的文献
     *
     * @param literatures 参考文献信息（包含类别）
     * @return 文献列表
     */
    public List<Literatures> selectLiteraturesWithChildren(Literatures literatures);
}
