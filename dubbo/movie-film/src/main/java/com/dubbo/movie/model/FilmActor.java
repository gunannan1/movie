package com.dubbo.movie.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 影片与演员映射表
 */
@Data
public class FilmActor {


    /**
     * 主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 影片编号,对应film
     */
    private Integer filmId;

    /**
     * 演员编号,对应actor
     */

    private Integer actorId;

    /**
     * 角色名称
     */
    private String roleName;


    @Override
    public String toString() {
        return "FilmActor{" +
        "id=" + id +
        ", filmId=" + filmId +
        ", actorId=" + actorId +
        ", roleName=" + roleName +
        "}";
    }
}
