package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.dubbo.movie.model.Film;
import com.dubbo.movie.vo.film.FilmDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 影片主表 Mapper 接口
 */
@Mapper
@Repository
public interface FilmMapper extends BaseMapper<Film> {
    FilmDetailVO getFilmDetailByName(@Param("filmName") String filmName);

    FilmDetailVO getFilmDetailById(@Param("id") String uuid);


}
