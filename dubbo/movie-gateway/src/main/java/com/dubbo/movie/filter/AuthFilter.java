package com.dubbo.movie.filter;


import com.dubbo.movie.config.auth.CurrentUser;
import com.dubbo.movie.config.auth.JwtProperties;
import com.dubbo.movie.exception.BizExceptionEnum;
import com.dubbo.movie.tips.ErrorTip;
import com.dubbo.movie.utils.JwtTokenUtil;
import com.dubbo.movie.utils.RenderUtil;
import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 对客户端请求的jwt token验证过滤器
 *，增加了忽略列表，
 */
public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
            System.out.println("拦截到了 auth 请求");

            //chain.doFilter方法将请求发给了过滤器链上的下一个filter，如果没有，就是你请求的资源了。
            chain.doFilter(request, response);
            return;
        }

        //配置忽略列表
        String ignoreUrl=jwtProperties.getIgnoreUrl();

        // /user/register
        String[] ignoreUrls = ignoreUrl.split(",");


        for(int i=0;i<ignoreUrls.length;i++){
            if(request.getServletPath().startsWith(ignoreUrls[i])){

                //如果设置忽略，则直接请求该路径
                chain.doFilter(request, response);
                return;
            }
        }


        //这里由前端把 Authorization字段 加入请求头
        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            System.out.println(requestHeader);

            // 通过Token获取userID，并且将之存入Threadlocal，以便后续业务调用
            String userId = jwtTokenUtil.getUsernameFromToken(authToken);
            if(userId == null){
                return;
            } else {
                CurrentUser.saveUserId(userId);
                System.out.println(userId);
            }


            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
                    return;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                return;
            }
        } else {
            //header没有带Bearer字段
            RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }
}