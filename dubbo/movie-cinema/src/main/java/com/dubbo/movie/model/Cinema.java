package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 影院信息表

 */
@Data
public class Cinema  {


    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 影院名称
     */
    @TableField("cinema_name")
    private String cinemaName;
    /**
     * 影院电话
     */
    @TableField("cinema_phone")
    private String cinemaPhone;
    /**
     * 品牌编号
     */
    @TableField("brand_id")
    private Integer brandId;
    /**
     * 地域编号
     */
    @TableField("area_id")
    private Integer areaId;
    /**
     * 包含的影厅类型,以#作为分割
     */
    @TableField("hall_ids")
    private String hallIds;
    /**
     * 影院图片地址
     */
    @TableField("img_address")
    private String imgAddress;
    /**
     * 影院地址
     */
    @TableField("cinema_address")
    private String cinemaAddress;
    /**
     * 最低票价
     */
    @TableField("minimum_price")
    private Integer minimumPrice;



    @Override
    public String toString() {
        return "Cinema{" +
        "id=" + id +
        ", cinemaName=" + cinemaName +
        ", cinemaPhone=" + cinemaPhone +
        ", brandId=" + brandId +
        ", areaId=" + areaId +
        ", hallIds=" + hallIds +
        ", imgAddress=" + imgAddress +
        ", cinemaAddress=" + cinemaAddress +
        ", minimumPrice=" + minimumPrice +
        "}";
    }
}
