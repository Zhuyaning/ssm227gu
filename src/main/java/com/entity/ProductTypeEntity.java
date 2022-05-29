package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品分类
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("shangpinfenlei")
public class ProductTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 商品分类
     */
    private String shangpinfenlei;


    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 设置：商品分类
     */
    public void setShangpinfenlei(String shangpinfenlei) {
        this.shangpinfenlei = shangpinfenlei;
    }

    /**
     * 获取：商品分类
     */
    public String getShangpinfenlei() {
        return shangpinfenlei;
    }

}
