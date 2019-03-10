package com.dubbo.movie.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 传输一些用户的个人信息
 */
@Data
public class UserInfoModel implements Serializable {


    private static final long serialVersionUID = -4669654083290332758L;

    private Integer uuid;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private int sex;
    private String birthday;
    private String lifeState;
    private String biography;
    private String address;
    private String headAddress;
    private long beginTime;
    private long updateTime;
}
