package com.dubbo.movie.dao;

import com.dubbo.movie.model.HallFilmInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 影厅电影信息表 Mapper 接口
 */
@Mapper
@Repository
public interface HallFilmInfoMapper extends BaseMapper<HallFilmInfo> {

}
