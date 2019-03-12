package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.vo.film.FilmInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 影片主表 Mapper 接口
 */
@Mapper
@Repository
public interface FilmInfoMapper extends BaseMapper<FilmInfoVO> {

}
