package com.dubbo.movie.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
public class PlatformBalance {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String platformName;

    private Double currentBalance;

    private Integer version;

    private Date beginTime;

    private Date updateTime;


}
