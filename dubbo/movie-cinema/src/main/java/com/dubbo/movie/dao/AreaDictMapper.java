package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.AreaDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 地域信息表 Mapper 接口
 */
@Mapper
@Repository
public interface AreaDictMapper extends BaseMapper<AreaDict> {

}
