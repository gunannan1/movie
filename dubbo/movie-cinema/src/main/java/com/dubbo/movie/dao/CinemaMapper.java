package com.dubbo.movie.dao;

import com.dubbo.movie.model.Cinema;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 影院信息表 Mapper 接口
 */
@Mapper
@Repository
public interface CinemaMapper extends BaseMapper<Cinema> {

}
