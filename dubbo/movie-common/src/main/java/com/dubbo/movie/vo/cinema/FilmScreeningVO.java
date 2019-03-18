package com.dubbo.movie.vo.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmScreeningVO implements Serializable {

    private String fieldId;
    private String beginTime;
    private String endTime;
    private String language;
    private String hallName;
    private String price;


}
