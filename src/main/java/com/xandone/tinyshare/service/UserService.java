package com.xandone.tinyshare.service;

import com.xandone.tinyshare.bean.UserBean;
import com.xandone.tinyshare.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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

    public UserBean addUser(String account,String psw,int visiteCode) throws Exception {
        UserBean userBean=new UserBean();
        userBean.setAccount(account);
        userBean.setPassword(psw);
        userBean.setRegistTime(new Date());
        userBean.setLastLoginTime(new Date());
        userMapper.addUser(userBean);
        return userBean;
    }


}
