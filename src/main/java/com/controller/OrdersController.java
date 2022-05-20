package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.OrdersEntity;
import com.entity.view.OrdersView;
import com.service.OrdersService;
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
 * 订单
 * 后端接口
 */
@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, OrdersEntity orders,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            orders.setUserid((Long) request.getSession().getAttribute("userId"));
        }

        EntityWrapper<OrdersEntity> ew = new EntityWrapper<>();
        PageUtils page = ordersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, OrdersEntity orders,
                       HttpServletRequest request) {
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<>();
        PageUtils page = ordersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(OrdersEntity orders) {
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(orders, "orders"));
        return Result.ok().put("data", ordersService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(OrdersEntity orders) {
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(orders, "orders"));
        OrdersView ordersView = ordersService.selectView(ew);
        return Result.ok("查询订单成功").put("data", ordersView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        OrdersEntity orders = ordersService.selectById(id);
        return Result.ok().put("data", orders);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        OrdersEntity orders = ordersService.selectById(id);
        return Result.ok().put("data", orders);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody OrdersEntity orders, HttpServletRequest request) {
        orders.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        orders.setUserid((Long) request.getSession().getAttribute("userId"));

        ordersService.insert(orders);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody OrdersEntity orders, HttpServletRequest request) {
        orders.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        ordersService.insert(orders);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody OrdersEntity orders, HttpServletRequest request) {
        ordersService.updateById(orders);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        ordersService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<OrdersEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            wrapper.eq("userid", request.getSession().getAttribute("userId"));
        }


        int count = ordersService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
