package com.xandone.tinyshare.controller;

import com.xandone.tinyshare.bean.AssetsBean;
import com.xandone.tinyshare.bean.UserBean;
import com.xandone.tinyshare.mapper.AssetsMapper;
import com.xandone.tinyshare.mapper.UserMapper;
import com.xandone.tinyshare.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2022/10/13 14:32
 * description：
 */
@Controller
@RequestMapping("/hell0")
public class HelloWorld {

    @Autowired
    AssetService assetService;

    @Autowired
    AssetsMapper mapper;

    @ResponseBody
    @RequestMapping("/123")
    public String hello(){
//        AssetsBean assetsBean=new AssetsBean();
//        assetsBean.setTypeName("小说");
//        assetsBean.setTitle("测试标题");
//        assetsBean.setAssetId(1);
//        assetsBean.setAssetUserId(12);
//        mapper.addAssets(assetsBean);
        return "hello123!";
    }
}
