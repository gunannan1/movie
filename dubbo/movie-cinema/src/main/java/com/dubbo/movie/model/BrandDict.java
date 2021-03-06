package com.dubbo.movie.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌信息表
 */
@Data
public class BrandDict {


    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 显示名称
     */
    @TableField("show_name")
    private String showName;

    @Override
    public String toString() {
        return "BrandDict{" +
        "id=" + id +
        ", showName=" + showName +
        "}";
    }
}
