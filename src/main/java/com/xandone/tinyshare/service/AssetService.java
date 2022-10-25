package com.xandone.tinyshare.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.tinyshare.bean.AssetsBean;
import com.xandone.tinyshare.common.BaseListResult;
import com.xandone.tinyshare.mapper.AssetsMapper;
import com.xandone.tinyshare.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2022/10/14 8:55
 * description：
 */
@Service
public class AssetService {

    @Autowired
    AssetsMapper assetsMapper;

    public AssetsBean addAssets(Map<String, Object> map) {
        AssetsBean assetsBean = new AssetsBean();
        assetsBean.setAssetUserId((Integer) map.get("userId"));
        assetsBean.setTitle((String) map.get("title"));
        assetsBean.setType((Integer) map.get("type"));
        assetsBean.setCoverImg((String) map.get("coverImg"));
        assetsBean.setAssetUserId((Integer) map.get("userId"));

        assetsBean.setAssetUrl((String) map.get("assetUrl"));
        assetsBean.setAssetCode((String) map.get("assetCode"));
        assetsBean.setContent((String) map.get("content"));
        assetsBean.setContentHtml((String) map.get("contentHtml"));
        int type = (Integer) map.get("type");
        assetsBean.setTypeName(SimpleUtils.getArtTypeName(type));
        assetsBean.setPostTime(new Date());
        assetsMapper.addAssets(assetsBean);
        return assetsBean;
    }

    public AssetsBean editAssets(Map<String, Object> map) {
        return new AssetsBean();
    }


    public BaseListResult getAllList(Integer page, Integer row, Integer type) {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<AssetsBean> list;
        if (type == null || type == 0) {
            list = assetsMapper.getAllList();
        } else {
            list = assetsMapper.getAllListByType(type);
        }
        int total = (int) new PageInfo<>(list).getTotal();

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    public AssetsBean getAssetById(String artId) throws Exception {
        AssetsBean assetsBean = assetsMapper.getAssetById(artId);
        return assetsBean;
    }

    public BaseListResult searchAssets(Integer page, Integer row, AssetsBean assetsBean) {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<AssetsBean> list;
        int type = assetsBean.getType();
        if (type == 0) {
            list = assetsMapper.searchAssets(assetsBean);
        } else {
            list = assetsMapper.searchAssetsbyType(assetsBean);
        }
        int total = (int) new PageInfo<>(list).getTotal();

        base.setData(list);
        base.setTotal(total);
        return base;
    }

}
