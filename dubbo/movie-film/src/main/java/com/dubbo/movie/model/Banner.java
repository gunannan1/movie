package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * banner信息表
 */
@Data
public class Banner  {

    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * banner图存放路径
     */
    private String bannerAddress;

    /**
     * banner点击跳转url
     */
    private String bannerUrl;

    /**
     * 是否弃用 0-失效,1-失效
     */
    private Integer isValid;



    @Override
    public String toString() {
        return "Banner{" +
        "id=" + id +
        ", bannerAddress=" + bannerAddress +
        ", bannerUrl=" + bannerUrl +
        ", isValid=" + isValid +
        "}";
    }
}
