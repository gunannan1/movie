package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.CategoryDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 类型信息表 Mapper 接口
 */
@Mapper
@Repository
public interface CategoryDictMapper extends BaseMapper<CategoryDict> {

}
