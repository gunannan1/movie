package com.dubbo.movie.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModel implements Serializable {


    private static final long serialVersionUID = 9132547006081784572L;

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

}
