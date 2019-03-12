package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.dubbo.movie.model.Actor;
import com.dubbo.movie.vo.film.ActorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 演员表 Mapper 接口

 */
@Mapper
@Repository
public interface ActorMapper extends BaseMapper<Actor> {

    List<ActorVO> getActors(@Param("filmId") String filmId);


}
