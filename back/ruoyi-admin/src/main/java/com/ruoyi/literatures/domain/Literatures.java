package com.ruoyi.literatures.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 参考文献对象 literatures
 * 
 * @author ws
 * @date 2025-05-26
 */
public class Literatures extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 文献id */
    private Long literaturesId;

    /** 文献分类 */
    @Excel(name = "文献分类")
    private String literaturesCategory;

    /** 文献名称 */
    @Excel(name = "文献名称")
    private String literaturesName;

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

    /** 文件 */
    //@Excel(name = "文件")
    private String fileRoad;

    /** 部门id */
    private Long deptId;

    /** 用户id */
    private Long userId;

    public void setLiteraturesId(Long literaturesId) 
    {
        this.literaturesId = literaturesId;
    }

    public Long getLiteraturesId() 
    {
        return literaturesId;
    }

    public void setLiteraturesCategory(String literaturesCategory) 
    {
        this.literaturesCategory = literaturesCategory;
    }

    public String getLiteraturesCategory() 
    {
        return literaturesCategory;
    }

    public void setLiteraturesName(String literaturesName) 
    {
        this.literaturesName = literaturesName;
    }

    public String getLiteraturesName() 
    {
        return literaturesName;
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
            .append("literaturesId", getLiteraturesId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("literaturesCategory", getLiteraturesCategory())
            .append("literaturesName", getLiteraturesName())
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
