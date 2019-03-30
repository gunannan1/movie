package com.dubbo.movie.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dubbo.movie.api.user.UserAPI;
import com.dubbo.movie.dao.UserMapper;
import com.dubbo.movie.model.User;
import com.dubbo.movie.utils.MD5Util;
import com.dubbo.movie.vo.user.UserInfoModel;
import com.dubbo.movie.vo.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Service(interfaceClass = UserAPI.class,loadbalance = "roundrobin")
public class UserServiceImpl implements UserAPI {

    @Autowired
    private UserMapper userDao;


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public int login(String username, String password) {
        // 根据登陆账号获取数据库信息
        User user = new User();
        user.setUserName(username);

        User result = userDao.selectOne(user);

        // 获取到的结果，然后与加密以后的密码做匹配
        if(result!=null && result.getId()>0){
            String md5Password = MD5Util.encrypt(password);
            if(result.getUserPwd().equals(md5Password)){
                return result.getId();
            }
        }
        return 0;
    }


    @Override
    public boolean register(UserModel userModel) {

        // 将注册信息实体转换为数据实体[user]
        User user = new User();
        user.setUserName(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setAddress(userModel.getAddress());
        user.setUserPhone(userModel.getPhone());

        // 创建时间和修改时间 -> current_timestamp
        user.setBeginTime(new Date());
        user.setUpdateTime(new Date());

        // 数据加密 【MD5混淆加密 + 盐值 -> Shiro加密】
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        user.setUserPwd(md5Password);

        // 将数据实体存入数据库
        Integer insert = userDao.insert(user);

        return insert>0;
    }

    /**
     * 查找和username匹配的字段的数量，存在用户名时返回错误
     * @param username
     * @return
     */
    @Override
    public boolean checkUsername(String username) {

        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name",username);
        Integer result = userDao.selectCount(entityWrapper);


        return !(result!=null && result>0);
    }


    /**
     * 根据主键id查询用户
     * @param id
     * @return
     */
    @Override
    public UserInfoModel getUserInfo(int id) {
        User user = userDao.selectById(id);

        // 将user转换UserInfoModel
        UserInfoModel userInfoModel = do2UserInfo(user);
        // 返回UserInfoModel
        return userInfoModel;
    }

    /**
     * 更新用户信息
     * @param userInfoModel
     * @return
     */
    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {

        User user = new User();
        user.setId(userInfoModel.getUuid());
        user.setNickName(userInfoModel.getNickname());
        user.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        user.setBirthday(userInfoModel.getBirthday());
        user.setBiography(userInfoModel.getBiography());
        user.setHeadUrl(userInfoModel.getHeadAddress());
        user.setEmail(userInfoModel.getEmail());
        user.setAddress(userInfoModel.getAddress());
        user.setUserPhone(userInfoModel.getPhone());
        user.setUserSex(userInfoModel.getSex());
        user.setUpdateTime(new Date());

        // DO存入数据库
        Integer integer = userDao.updateById(user);
        if(integer>0){
            // 将数据从数据库中读取出来
            UserInfoModel userInfo = getUserInfo(user.getId());
            // 将结果返回给前端
            return userInfo;
        }else{
            return null;
        }
    }

    /**
     * User实体类转换成UserInfoModel类
     * @param user
     * @return
     */
    private UserInfoModel do2UserInfo(User user){
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUuid(user.getId());
        userInfoModel.setHeadAddress(user.getHeadUrl());
        userInfoModel.setPhone(user.getUserPhone());
        userInfoModel.setEmail(user.getEmail());
        userInfoModel.setUsername(user.getUserName());
        userInfoModel.setNickname(user.getNickName());
        userInfoModel.setLifeState(""+user.getLifeState());
        userInfoModel.setBirthday(user.getBirthday());
        userInfoModel.setAddress(user.getAddress());
        userInfoModel.setSex(user.getUserSex());
        userInfoModel.setBiography(user.getBiography());
        userInfoModel.setBeginTime(user.getBeginTime().getTime());
        userInfoModel.setUpdateTime(user.getUpdateTime().getTime());

        return userInfoModel;
    }
}
