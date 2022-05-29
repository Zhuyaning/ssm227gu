package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ProductReviewEntity;
import com.entity.view.ProductReviewView;
import com.service.ProductReviewService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * 商品信息评论表
 * 后端接口
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/discussshangpinxinxi")
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, ProductReviewEntity discussshangpinxinxi,
                       HttpServletRequest request) {

        EntityWrapper<ProductReviewEntity> ew = new EntityWrapper<>();
        PageUtils page = productReviewService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpinxinxi), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, ProductReviewEntity discussshangpinxinxi,
                       HttpServletRequest request) {
        EntityWrapper<ProductReviewEntity> ew = new EntityWrapper<>();
        PageUtils page = productReviewService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpinxinxi), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(ProductReviewEntity discussshangpinxinxi) {
        EntityWrapper<ProductReviewEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(discussshangpinxinxi, "discussshangpinxinxi"));
        return Result.ok().put("data", productReviewService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(ProductReviewEntity discussshangpinxinxi) {
        EntityWrapper<ProductReviewEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(discussshangpinxinxi, "discussshangpinxinxi"));
        ProductReviewView discussshangpinxinxiView = productReviewService.selectView(ew);
        return Result.ok("查询商品信息评论表成功").put("data", discussshangpinxinxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        ProductReviewEntity discussshangpinxinxi = productReviewService.selectById(id);
        return Result.ok().put("data", discussshangpinxinxi);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        ProductReviewEntity discussshangpinxinxi = productReviewService.selectById(id);
        return Result.ok().put("data", discussshangpinxinxi);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ProductReviewEntity discussshangpinxinxi, HttpServletRequest request) {
        discussshangpinxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        productReviewService.insert(discussshangpinxinxi);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody ProductReviewEntity discussshangpinxinxi, HttpServletRequest request) {
        discussshangpinxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        productReviewService.insert(discussshangpinxinxi);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ProductReviewEntity discussshangpinxinxi) {
        productReviewService.updateById(discussshangpinxinxi);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        productReviewService.deleteBatchIds(Arrays.asList(ids));
        return Result.ok();
    }

    /**
     * 提醒接口
     */
    @RequestMapping("/remind/{columnName}/{type}")
    public Result remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
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

        Wrapper<ProductReviewEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = productReviewService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
