package com.dubbo.movie.dao;

import com.dubbo.movie.model.HallDict;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 地域信息表 Mapper 接口
 */
@Mapper
@Repository
public interface HallDictMapper extends BaseMapper<HallDict> {

}
