package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.StoreupEntity;
import com.entity.view.StoreupView;
import com.service.StoreupService;
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
 * 收藏表
 * 后端接口
 */
@CrossOrigin
@RestController
@RequestMapping("/storeup")
public class StoreupController {
    @Autowired
    private StoreupService storeupService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, StoreupEntity storeup,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            storeup.setUserid((Long) request.getSession().getAttribute("userId"));
        }

        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, StoreupEntity storeup,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            storeup.setUserid((Long) request.getSession().getAttribute("userId"));
        }

        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(StoreupEntity storeup) {
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(storeup, "storeup"));
        return Result.ok().put("data", storeupService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(StoreupEntity storeup) {
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(storeup, "storeup"));
        StoreupView storeupView = storeupService.selectView(ew);
        return Result.ok("查询收藏表成功").put("data", storeupView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        StoreupEntity storeup = storeupService.selectById(id);
        return Result.ok().put("data", storeup);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        StoreupEntity storeup = storeupService.selectById(id);
        return Result.ok().put("data", storeup);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody StoreupEntity storeup, HttpServletRequest request) {
        storeup.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        storeup.setUserid((Long) request.getSession().getAttribute("userId"));

        storeupService.insert(storeup);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody StoreupEntity storeup, HttpServletRequest request) {
        storeup.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        storeup.setUserid((Long) request.getSession().getAttribute("userId"));

        storeupService.insert(storeup);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody StoreupEntity storeup) {
        storeupService.updateById(storeup);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        storeupService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<StoreupEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            wrapper.eq("userid", request.getSession().getAttribute("userId"));
        }


        int count = storeupService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
