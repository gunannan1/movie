package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 年代信息表
 */
@Data
public class YearDict {

    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 显示名称
     */
    private String showName;


    @Override
    public String toString() {
        return "YearDict{" +
        "id=" + id +
        ", showName=" + showName +
        "}";
    }
}
