package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 影片主表
 */
@Data
public class FilmInfo {


    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 影片编号
     */
    private String filmId;
    /**
     * 影片英文名称
     */
    private String filmEnName;
    /**
     * 影片评分
     */
    private String filmScore;
    /**
     * 评分人数,以万为单位
     */
    private Integer filmScoreNum;
    /**
     * 播放时长，以分钟为单位，不足取整
     */
    private Integer filmLength;
    /**
     * 影片介绍
     */
    private String biography;
    /**
     * 导演编号
     */
    private Integer directorId;
    /**
     * 影片图片集地址,多个图片以逗号分隔
     */
    private String filmImgs;

    @Override
    public String toString() {
        return "FilmInfoVO{" +
        "id=" + id +
        ", filmId=" + filmId +
        ", filmEnName=" + filmEnName +
        ", filmScore=" + filmScore +
        ", filmScoreNum=" + filmScoreNum +
        ", filmLength=" + filmLength +
        ", biography=" + biography +
        ", directorId=" + directorId +
        ", filmImgs=" + filmImgs +
        "}";
    }
}
