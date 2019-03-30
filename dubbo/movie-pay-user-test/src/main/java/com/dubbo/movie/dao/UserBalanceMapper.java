package com.dubbo.movie.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dubbo.movie.model.UserBalance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserBalanceMapper extends BaseMapper<UserBalance> {

    @Update("update user_balance set current_balance = current_balance -  #{money},version= version + 1 where id = #{userId} and current_balance > #{money} and version = #{version}")
     int decreaseBalance(@Param("userId") Integer userId, @Param("version") Integer version, @Param("money") Double money);
}
