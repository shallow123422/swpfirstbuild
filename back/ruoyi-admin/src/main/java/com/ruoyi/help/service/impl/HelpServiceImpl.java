package com.ruoyi.help.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.help.mapper.HelpMapper;
import com.ruoyi.help.domain.Help;
import com.ruoyi.help.service.IHelpService;

/**
 * 辅助功能Service业务层处理
 * 
 * @author ws
 * @date 2025-06-04
 */
@Service
public class HelpServiceImpl implements IHelpService 
{
    @Autowired
    private HelpMapper helpMapper;

    /**
     * 查询辅助功能
     * 
     * @param papersId 辅助功能主键
     * @return 辅助功能
     */
    @Override
    public Help selectHelpByPapersId(Long papersId)
    {
        return helpMapper.selectHelpByPapersId(papersId);
    }

    /**
     * 查询辅助功能列表
     * 
     * @param help 辅助功能
     * @return 辅助功能
     */
    @Override
    public List<Help> selectHelpList(Help help)
    {
        return helpMapper.selectHelpList(help);
    }

    /**
     * 新增辅助功能
     * 
     * @param help 辅助功能
     * @return 结果
     */
    @Override
    public int insertHelp(Help help)
    {
        return helpMapper.insertHelp(help);
    }

    /**
     * 修改辅助功能
     * 
     * @param help 辅助功能
     * @return 结果
     */
    @Override
    public int updateHelp(Help help)
    {
        return helpMapper.updateHelp(help);
    }

    /**
     * 批量删除辅助功能
     * 
     * @param papersIds 需要删除的辅助功能主键
     * @return 结果
     */
    @Override
    public int deleteHelpByPapersIds(Long[] papersIds)
    {
        return helpMapper.deleteHelpByPapersIds(papersIds);
    }

    /**
     * 删除辅助功能信息
     * 
     * @param papersId 辅助功能主键
     * @return 结果
     */
    @Override
    public int deleteHelpByPapersId(Long papersId)
    {
        return helpMapper.deleteHelpByPapersId(papersId);
    }

    // HelpServiceImpl.java 中添加：
// HelpServiceImpl.java 中的 getCoAuthors 方法
    @Override
    public List<Map<String, Object>> getCoAuthors(String authorName) {
        List<Help> papers = helpMapper.selectHelpList(new Help());
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Integer> coAuthorCount = new HashMap<>();

        for (Help paper : papers) {
            String authors = paper.getAuthor();
            if (authors != null && authors.contains(authorName)) {
                // 分割作者名，假设用逗号分隔
                String[] authorList = authors.split("，");
                for (String author : authorList) {
                    author = author.trim();
                    if (!author.equals(authorName)) {
                        coAuthorCount.merge(author, 1, Integer::sum);
                    }
                }
            }
        }

        // 转换为前端需要的格式
        for (Map.Entry<String, Integer> entry : coAuthorCount.entrySet()) {
            Map<String, Object> item = new HashMap<>();
            item.put("coAuthor", entry.getKey());  // 使用 coAuthor 作为键名
            item.put("count", entry.getValue());    // 使用 count 作为键名
            result.add(item);
        }

        return result;
    }

    @Override
    public Map<String, Object> calculateWorkload(String authorName) {
        List<Help> papers = helpMapper.selectHelpList(new Help());
        Map<String, Object> result = new HashMap<>();

        int totalScore = 0;
        int q1Count = 0;
        int q2Count = 0;
        int q3Count = 0;
        int q4Count = 0;

        for (Help paper : papers) {
            if (paper.getAuthor() != null && paper.getAuthor().contains(authorName)) {
                String journal = paper.getJournal();
                if (journal != null) {
                    switch (journal.trim()) {
                        case "一区":
                            totalScore += 300;
                            q1Count++;
                            break;
                        case "二区":
                            totalScore += 150;
                            q2Count++;
                            break;
                        case "三区":
                            totalScore += 100;
                            q3Count++;
                            break;
                        case "四区":
                            totalScore += 60;
                            q4Count++;
                            break;
                    }
                }
            }
        }

        result.put("totalScore", totalScore);
        result.put("q1Count", q1Count);
        result.put("q2Count", q2Count);
        result.put("q3Count", q3Count);
        result.put("q4Count", q4Count);

        return result;
    }
}
