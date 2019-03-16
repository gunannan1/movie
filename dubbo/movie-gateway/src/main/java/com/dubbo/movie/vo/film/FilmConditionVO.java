package com.dubbo.movie.vo.film;


import lombok.Data;

import java.util.List;

@Data
public class FilmConditionVO {

    private List<CatVO> catInfo;
    private List<SourceVO> sourceInfo;
    private List<YearVO> yearInfo;

}
