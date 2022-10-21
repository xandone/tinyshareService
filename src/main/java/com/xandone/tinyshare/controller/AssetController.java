package com.xandone.tinyshare.controller;

import com.xandone.tinyshare.bean.AssetsBean;
import com.xandone.tinyshare.common.BaseListResult;
import com.xandone.tinyshare.common.BaseResult;
import com.xandone.tinyshare.common.IReturnCode;
import com.xandone.tinyshare.config.ProjectConstant;
import com.xandone.tinyshare.service.AssetService;
import com.xandone.tinyshare.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2022/10/20 15:02
 * description：
 */
@Controller
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    AssetService assetService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addArt(@RequestBody Map<String, Object> map) {
        BaseResult baseResult = new BaseResult();
        AssetsBean assetsBean;
        try {
            int userId= (int) map.get("userId");
            System.out.println("userId="+map.get("userId"));
            if (ProjectConstant.ADMIN_ID!=userId) {
                baseResult.setCode(IReturnCode.ERROR_NO_ADMIN_CODE);
                baseResult.setMsg(IReturnCode.MES_NO_ADMIN);
                return baseResult;
            }
            if (map.get("assetId") == null || SimpleUtils.isEmpty( map.get("assetId"))) {
                assetsBean = assetService.addAssets(map);
            } else {
                assetsBean = assetService.editAssets(map);
            }
            List<AssetsBean> list = new ArrayList<>();
            list.add(assetsBean);
            baseResult.setData(list);
            baseResult.setCode(IReturnCode.SUCCESS);

        } catch (
                Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/assetlist")
    @ResponseBody
    public BaseListResult getAssetList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row,
                                         Integer type) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = assetService.getAllList(page, row, type);
            if (result != null) {
                result.setCode(IReturnCode.SUCCESS);
                result.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(IReturnCode.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }


    @RequestMapping(value = "/details")
    @ResponseBody
    public BaseResult getArtDetailsById(@RequestParam(value = "assetId") String artId) {
        BaseResult baseResult = new BaseResult();
        try {
            AssetsBean articleBean = assetService.getAssetById(artId);

            List<AssetsBean> list = new ArrayList<>();
            list.add(articleBean);
            baseResult.setData(list);
            baseResult.setCode(IReturnCode.SUCCESS);
            baseResult.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }
}
