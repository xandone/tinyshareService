package com.xandone.tinyshare.mapper;

import com.xandone.tinyshare.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {
    UserBean getUserByAccount(String account);

    void updateUser(UserBean userBean);
}
