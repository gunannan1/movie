package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.Screening;
import com.dubbo.movie.vo.cinema.FilmInfoVO;
import com.dubbo.movie.vo.cinema.HallInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 放映场次表 Mapper 接口
 */
@Mapper
@Repository
public interface ScreeningMapper extends BaseMapper<Screening> {
    List<FilmInfoVO> getFilmInfos(@Param("cinemaId") int cinemaId);

    HallInfoVO getHallInfo(@Param("fieldId") int fieldId);

    FilmInfoVO getFilmInfoById(@Param("fieldId") int fieldId);


}
