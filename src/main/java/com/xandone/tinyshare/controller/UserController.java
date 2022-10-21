package com.xandone.tinyshare.controller;

import com.xandone.tinyshare.bean.UserBean;
import com.xandone.tinyshare.common.BaseResult;
import com.xandone.tinyshare.common.IReturnCode;
import com.xandone.tinyshare.service.UserService;
import com.xandone.tinyshare.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2022/10/21 16:15
 * description：
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestParam(value = "account") String account,
                            @RequestParam(value = "psw") String psw) {
        BaseResult baseResult = new BaseResult();
        List<UserBean> list = new ArrayList<>();
        UserBean adminBean;
        try {
            adminBean = userService.getUserByAccount(account);
            if (adminBean == null) {
                baseResult.setMsg("不存在该用户");
                baseResult.setCode(IReturnCode.ERROR_CODE);
                return baseResult;
            } else if (!adminBean.getPassword().equals(psw)) {
                baseResult.setMsg("密码错误");
                baseResult.setCode(IReturnCode.ERROR_CODE);
                return baseResult;
            } else {
                adminBean.setToken(TokenUtils.getToken(adminBean.getUserId()));
                list.add(adminBean);
                baseResult.setData(list);
                baseResult.setCode(IReturnCode.SUCCESS);
                baseResult.setMsg("登录成功");

                adminBean.setLastLoginTime(new Date());
                userService.updateUser(adminBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
            baseResult.setCode(IReturnCode.ERROR_CODE);
        }
        return baseResult;
    }
}
