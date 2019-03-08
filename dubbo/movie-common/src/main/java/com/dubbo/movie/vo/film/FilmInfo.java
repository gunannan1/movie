package com.dubbo.movie.vo.film;

import lombok.Data;

import java.io.Serializable;

/**
 * 电影信息
 */
@Data
public class FilmInfo implements Serializable {

    private String filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    private int expectNum;
    private String showTime;
    private int boxNum;//排名
    private String score; //分数，和上面那个score一样，只是根据接口文档新定义一个

}
