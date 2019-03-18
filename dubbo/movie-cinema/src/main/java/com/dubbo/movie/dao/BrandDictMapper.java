package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.BrandDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 品牌信息表 Mapper 接口
 */
@Mapper
@Repository
public interface BrandDictMapper extends BaseMapper<BrandDict> {

}
