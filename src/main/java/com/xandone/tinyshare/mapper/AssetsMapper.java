package com.xandone.tinyshare.mapper;

import com.xandone.tinyshare.bean.AssetsBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2022/10/13 17:09
 * description：
 */
@Repository
@Mapper
public interface AssetsMapper {

    List<AssetsBean> getAllList();

    List<AssetsBean> getAllListByType(int type);

    void addAssets(AssetsBean assetsBean);

    void editAssets(AssetsBean assetsBean);

    AssetsBean getAssetById(String artId);

    List<AssetsBean> searchAssets(AssetsBean assetsBean);

    List<AssetsBean> searchAssetsbyType(AssetsBean assetsBean);

}
