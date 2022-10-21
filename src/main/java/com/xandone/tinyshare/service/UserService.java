package com.xandone.tinyshare.service;

import com.xandone.tinyshare.bean.UserBean;
import com.xandone.tinyshare.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：xandone
 * created on  ：2022/10/21 16:16
 * description：
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public UserBean getUserByAccount(String account) throws Exception {
        return userMapper.getUserByAccount(account);
    }

    public void updateUser(UserBean userBean) throws Exception {
        userMapper.updateUser(userBean);
    }



}
