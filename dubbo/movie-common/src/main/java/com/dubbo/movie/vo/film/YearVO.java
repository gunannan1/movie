package com.dubbo.movie.vo.film;

import lombok.Data;

import java.io.Serializable;

/**
 * 电影年代vo
 */
@Data
public class YearVO implements Serializable {

    private String yearId;
    private String yearName;
    private boolean isActive;

}
