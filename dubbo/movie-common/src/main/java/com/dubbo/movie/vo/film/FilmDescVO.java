package com.dubbo.movie.vo.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmDescVO implements Serializable {

    private String biography;
    private String filmId;

}
