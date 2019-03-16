package com.dubbo.movie.vo.film;


import lombok.Data;

import java.util.List;

/**
 * 首页需要的对象
 */
@Data
public class FilmIndexVO {

    private List<BannerVO> banners;
    private FilmVO hotFilms;
    private FilmVO soonFilms;
    private List<FilmInformationVO> boxRanking;
    private List<FilmInformationVO> expectRanking;
    private List<FilmInformationVO> top100;

}
