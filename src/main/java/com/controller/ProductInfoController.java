package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ProductInfoEntity;
import com.entity.view.ProductInfoView;
import com.service.ProductInfoService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 商品信息
 * 后端接口
 */
@RestController
@CrossOrigin
@RequestMapping("/shangpinxinxi")
public class ProductInfoController {


    private ProductInfoService productInfoService;

    @Autowired
    public ProductInfoController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(ProductInfoEntity productInfo, @RequestParam Map<String, Object> params) {

        EntityWrapper<ProductInfoEntity> ew = new EntityWrapper<>();
        PageUtils page = productInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, productInfo), params), params));
        return Result.ok().put("productList", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, ProductInfoEntity productInfo) {
        EntityWrapper<ProductInfoEntity> ew = new EntityWrapper<>();
        PageUtils page = productInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, productInfo), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(ProductInfoEntity productInfo) {
        EntityWrapper<ProductInfoEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(productInfo, "shangpinxinxi"));
        return Result.ok().put("data", productInfoService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(ProductInfoEntity productInfo) {
        EntityWrapper<ProductInfoEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(productInfo, "shangpinxinxi"));
        ProductInfoView shangpinxinxiView = productInfoService.selectView(ew);
        return Result.ok("查询商品信息成功").put("data", shangpinxinxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        ProductInfoEntity productInfo = productInfoService.selectById(id);
        productInfo.setClicknum(productInfo.getClicknum() + 1);
        productInfo.setClicktime(new Date());
        productInfoService.updateById(productInfo);
        return Result.ok().put("data", productInfo);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        ProductInfoEntity productInfo = productInfoService.selectById(id);
        productInfo.setClicknum(productInfo.getClicknum() + 1);
        productInfo.setClicktime(new Date());
        productInfoService.updateById(productInfo);
        return Result.ok().put("data", productInfo);
    }


    /**
     * 商品上架
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ProductInfoEntity product) {
        product.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        productInfoService.insert(product);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody ProductInfoEntity productInfo) {
        productInfo.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        productInfoService.insert(productInfo);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ProductInfoEntity productInfo) {
        productInfoService.updateById(productInfo);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        productInfoService.deleteBatchIds(Arrays.asList(ids));
        return Result.ok();
    }

    /**
     * 根据id删除
     * @param id 商品id
     * @return Result.ok
     */
    @RequestMapping("/deleteByID")
    public Result deleteByID(@RequestParam Long id) {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(id);
        productInfoService.deleteBatchIds(ids);
        return Result.ok();
    }

    /**
     * 提醒接口
     */
    @RequestMapping("/remind/{columnName}/{type}")
    public Result remindCount(@PathVariable("columnName") String columnName,
                              @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
        map.put("column", columnName);
        map.put("type", type);

        if (type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if (map.get("remindstart") != null) {
                int remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                int remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        Wrapper<ProductInfoEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = productInfoService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }

    /**
     * 前端智能排序
     */
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public Result autoSort(@RequestParam Map<String, Object> params, ProductInfoEntity shangpinxinxi, HttpServletRequest request, String pre) {
        EntityWrapper<ProductInfoEntity> ew = new EntityWrapper<>();
        Map<String, Object> newMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            String newKey = entry.getKey();
            if (pre.endsWith(".")) {
                newMap.put(pre + newKey, entry.getValue());
            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newKey, entry.getValue());
            } else {
                newMap.put(pre + "." + newKey, entry.getValue());
            }
        }
        params.put("sort", "clicknum");

        params.put("order", "desc");
        PageUtils page = productInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinxinxi), params), params));
        return Result.ok().put("data", page);
    }


}
