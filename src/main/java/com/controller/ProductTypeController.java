package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ProductTypeEntity;
import com.entity.view.ProductTypeView;
import com.service.ProductTypeService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 商品分类
 * 后端接口
 */
@RestController
@CrossOrigin
@RequestMapping("/shangpinfenlei")
public class ProductTypeController {

    private ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(ProductTypeEntity productType, @RequestParam Map<String, Object> params) {
        EntityWrapper<ProductTypeEntity> ew = new EntityWrapper<>();
        PageUtils page = productTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, productType), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(ProductTypeEntity productType, @RequestParam Map<String, Object> params) {
        EntityWrapper<ProductTypeEntity> ew = new EntityWrapper<>();
        PageUtils page = productTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, productType), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(ProductTypeEntity productType) {
        EntityWrapper<ProductTypeEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(productType, "shangpinfenlei"));
        return Result.ok().put("data", productTypeService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(ProductTypeEntity productType) {
        EntityWrapper<ProductTypeEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(productType, "shangpinfenlei"));
        ProductTypeView shangpinfenleiView = productTypeService.selectView(ew);
        return Result.ok("查询商品分类成功").put("data", shangpinfenleiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        ProductTypeEntity type = productTypeService.selectById(id);
        return Result.ok().put("data", type);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        ProductTypeEntity productType = productTypeService.selectById(id);
        return Result.ok().put("data", productType);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ProductTypeEntity productType) {
        productType.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        productTypeService.insert(productType);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody ProductTypeEntity productType) {
        productType.setId((long) (new Date().getTime() + Math.floor(Math.random() * 1000)));
        productTypeService.insert(productType);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ProductTypeEntity productType) {
        productTypeService.updateById(productType);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        productTypeService.deleteBatchIds(Arrays.asList(ids));
        return Result.ok();
    }

    /**
     * 根据id删除
     * @param id 商品分类id
     * @return Result.ok
     */
        @RequestMapping("/deleteByID")
    public Result deleteByID( Long id) {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(id);
        productTypeService.deleteBatchIds(ids);
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
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        Wrapper<ProductTypeEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = productTypeService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
