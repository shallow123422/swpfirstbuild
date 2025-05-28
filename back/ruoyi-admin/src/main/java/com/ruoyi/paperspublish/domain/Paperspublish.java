package com.ruoyi.paperspublish.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 我的论文对象 papers
 * 
 * @author ws
 * @date 2025-05-25
 */
public class Paperspublish extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 论文id */
    private Long papersId;

    /** 论文分类 */
    @Excel(name = "论文分类")
    private String papersCategory;

    /** 论文名称 */
    @Excel(name = "论文名称")
    private String papersName;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keywords;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 期刊 */
    @Excel(name = "期刊")
    private String journal;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件路径 */
    //@Excel(name = "文件路径")
    private String fileRoad;

    /** 部门id */
    private Long deptId;
    /** 用户id */
    private Long userId;

    public void setPapersId(Long papersId)
    {
        this.papersId = papersId;
    }

    public Long getPapersId() 
    {
        return papersId;
    }

    public void setPapersCategory(String papersCategory) 
    {
        this.papersCategory = papersCategory;
    }

    public String getPapersCategory() 
    {
        return papersCategory;
    }

    public void setPapersName(String papersName) 
    {
        this.papersName = papersName;
    }

    public String getPapersName() 
    {
        return papersName;
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }

    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }

    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    public void setJournal(String journal) 
    {
        this.journal = journal;
    }

    public String getJournal() 
    {
        return journal;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setFileRoad(String fileRoad) 
    {
        this.fileRoad = fileRoad;
    }

    public String getFileRoad() 
    {
        return fileRoad;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("papersId", getPapersId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("papersCategory", getPapersCategory())
            .append("papersName", getPapersName())
            .append("author", getAuthor())
            .append("keywords", getKeywords())
            .append("uploadTime", getUploadTime())
            .append("journal", getJournal())
            .append("fileName", getFileName())
            .append("fileRoad", getFileRoad())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .toString();
    }
}
