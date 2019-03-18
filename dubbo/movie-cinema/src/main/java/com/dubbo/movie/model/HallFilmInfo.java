package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 影厅电影信息表
 */
@Data
public class HallFilmInfo {


    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 电影编号
     */
    @TableField("film_id")
    private Integer filmId;
    /**
     * 电影名称
     */
    @TableField("film_name")
    private String filmName;
    /**
     * 电影时长
     */
    @TableField("film_length")
    private String filmLength;
    /**
     * 电影类型
     */
    @TableField("film_cats")
    private String filmCats;
    /**
     * 电影语言
     */
    @TableField("film_language")
    private String filmLanguage;
    /**
     * 演员列表
     */
    private String actors;
    /**
     * 图片地址
     */
    @TableField("img_address")
    private String imgAddress;


    @Override
    public String toString() {
        return "HallFilmInfo{" +
        "id=" + id +
        ", filmId=" + filmId +
        ", filmName=" + filmName +
        ", filmLength=" + filmLength +
        ", filmCats=" + filmCats +
        ", filmLanguage=" + filmLanguage +
        ", actors=" + actors +
        ", imgAddress=" + imgAddress +
        "}";
    }
}
