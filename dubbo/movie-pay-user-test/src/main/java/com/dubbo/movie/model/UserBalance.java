package com.dubbo.movie.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 用户账户
 */
@Data
public class UserBalance {

    /**
     * userId
     */
    private Integer id;

    private Double currentBalance;

    private Integer version;

    private Date beginTime;

    private Date updateTime;

}
