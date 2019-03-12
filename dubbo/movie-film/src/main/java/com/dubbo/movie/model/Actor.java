package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 演员表
 *
 */
@Data
public class Actor{

    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 演员名称
     */
    private String actorName;


    /**
     * 演员图片位置
     */
    private String actorImg;


    @Override
    public String toString() {
        return "Actor{" +
        "id=" + id +
        ", actorName=" + actorName +
        ", actorImg=" + actorImg +
        "}";
    }
}
