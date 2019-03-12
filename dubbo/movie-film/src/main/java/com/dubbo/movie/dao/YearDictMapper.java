package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.YearDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 年代信息表 Mapper 接口
 */
@Mapper
@Repository
public interface YearDictMapper extends BaseMapper<YearDict> {

}
