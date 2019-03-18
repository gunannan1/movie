package com.dubbo.movie.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 放映场次表
 */
@Data
public class Screening {


    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 影院编号
     */
    @TableField("cinema_id")
    private Integer cinemaId;
    /**
     * 电影编号
     */
    @TableField("film_id")
    private Integer filmId;
    /**
     * 开始时间
     */
    @TableField("begin_time")
    private String beginTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private String endTime;
    /**
     * 放映厅类型编号
     */
    @TableField("hall_id")
    private Integer hallId;
    /**
     * 放映厅名称
     */
    @TableField("hall_name")
    private String hallName;
    /**
     * 票价
     */
    @TableField("price")
    private Integer price;

    /**
     * 剩余座位数量，测试用
     */
    @TableField("seat_count")
    private Integer seatCount;

    /**
     * 乐观锁版本号，测试用
     */
    @TableField("version")
    private Integer version;



    @Override
    public String toString() {
        return "Screening{" +
        "id=" + id +
        ", cinemaId=" + cinemaId +
        ", filmId=" + filmId +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", hallId=" + hallId +
        ", hallName=" + hallName +
        ", price=" + price +
        "}";
    }
}
