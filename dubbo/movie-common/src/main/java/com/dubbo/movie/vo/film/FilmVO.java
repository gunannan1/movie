package com.dubbo.movie.vo.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 热映电影、未来将上映的电影
 * 因为内容是一样的，所以合到一个vo
 */
@Data
public class FilmVO  implements Serializable {

    private int filmNum;
    private int nowPage;
    private int totalPage;
    private List<FilmInformationVO> filmInformationVO;

}
