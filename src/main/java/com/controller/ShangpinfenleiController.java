package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ShangpinfenleiEntity;
import com.entity.view.ShangpinfenleiView;
import com.service.ShangpinfenleiService;
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
 * 商品分类
 * 后端接口
 */
@RestController
@CrossOrigin
@RequestMapping("/shangpinfenlei")
public class ShangpinfenleiController {

    private ShangpinfenleiService shangpinfenleiService;

    @Autowired
    public ShangpinfenleiController(ShangpinfenleiService shangpinfenleiService) {
        this.shangpinfenleiService = shangpinfenleiService;
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, ShangpinfenleiEntity shangpinfenlei) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<>();
        PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinfenlei), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, ShangpinfenleiEntity shangpinfenlei) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<>();
        PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinfenlei), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(ShangpinfenleiEntity shangpinfenlei) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(shangpinfenlei, "shangpinfenlei"));
        return Result.ok().put("data", shangpinfenleiService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(ShangpinfenleiEntity shangpinfenlei) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(shangpinfenlei, "shangpinfenlei"));
        ShangpinfenleiView shangpinfenleiView = shangpinfenleiService.selectView(ew);
        return Result.ok("查询商品分类成功").put("data", shangpinfenleiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        ShangpinfenleiEntity shangpinfenlei = shangpinfenleiService.selectById(id);
        return Result.ok().put("data", shangpinfenlei);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        ShangpinfenleiEntity shangpinfenlei = shangpinfenleiService.selectById(id);
        return Result.ok().put("data", shangpinfenlei);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ShangpinfenleiEntity shangpinfenlei) {
        shangpinfenlei.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        shangpinfenleiService.insert(shangpinfenlei);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody ShangpinfenleiEntity shangpinfenlei) {
        shangpinfenlei.setId((long) (new Date().getTime() + Math.floor(Math.random() * 1000)));
        shangpinfenleiService.insert(shangpinfenlei);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ShangpinfenleiEntity shangpinfenlei) {
        shangpinfenleiService.updateById(shangpinfenlei);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        shangpinfenleiService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<ShangpinfenleiEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = shangpinfenleiService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
