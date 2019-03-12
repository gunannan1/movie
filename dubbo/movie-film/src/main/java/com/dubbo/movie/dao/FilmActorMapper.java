package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.FilmActor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 影片与演员映射表 Mapper 接口
 */

@Mapper
@Repository
public interface FilmActorMapper extends BaseMapper<FilmActor> {

}
