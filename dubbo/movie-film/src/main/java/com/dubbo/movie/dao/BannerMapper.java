package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * banner信息表 Mapper 接口

 */
@Mapper
@Repository
public interface BannerMapper extends BaseMapper<Banner> {


}
