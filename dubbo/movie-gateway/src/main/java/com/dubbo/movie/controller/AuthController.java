package com.dubbo.movie.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.dubbo.movie.api.user.UserAPI;
import com.dubbo.movie.utils.JwtTokenUtil;
import com.dubbo.movie.vo.ResponseVO;
import com.dubbo.movie.vo.auth.AuthRequest;
import com.dubbo.movie.vo.auth.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求验证
 *
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    //check=false,启动时不进行验证服务提供者的可用性
    @Reference(interfaceClass= UserAPI.class,check = false)
    private UserAPI userAPI;

    @RequestMapping(value = "/auth")
    public ResponseVO createAuthenticationToken(AuthRequest authRequest) {

        boolean validate = true;

        // 去掉guns自身携带的用户名密码验证机制，使用我们自己的
        int userId = userAPI.login(authRequest.getUserName(),authRequest.getPassword());
        if(userId==0){
            validate = false;
        }

        if (validate) {
            // randomKey和token已经生成完毕
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(""+userId, randomKey);
            // 返回值
            return ResponseVO.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVO.serviceFail("用户名或密码错误");
        }
    }

}
