package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.CartEntity;
import com.entity.view.CartView;
import com.service.CartService;
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
 * 购物车表
 * 后端接口
 */
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    static final String RE_MIND_START = "remindstart";
    static final String USER_ID = "userId";
    static final String RE_MIND_END = "remindend";

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, CartEntity cart,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            cart.setUserid((Long) request.getSession().getAttribute(USER_ID));
        }

        EntityWrapper<CartEntity> ew = new EntityWrapper<>();
        PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, CartEntity cart) {
        EntityWrapper<CartEntity> ew = new EntityWrapper<>();
        PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(CartEntity cart) {
        EntityWrapper<CartEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(cart, "cart"));
        return Result.ok().put("data", cartService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(CartEntity cart) {
        EntityWrapper<CartEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(cart, "cart"));
        CartView cartView = cartService.selectView(ew);
        return Result.ok("查询购物车表成功").put("data", cartView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        CartEntity cart = cartService.selectById(id);
        return Result.ok().put("data", cart);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        CartEntity cart = cartService.selectById(id);
        return Result.ok().put("data", cart);
    }


    /**
     * 商品加入购物车
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CartEntity cart, HttpServletRequest request) {
        cart.setId((long) (new Date().getTime() + Math.floor(Math.random() * 1000)));
        cart.setUserid((Long) request.getSession().getAttribute(USER_ID));
        cartService.insert(cart);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CartEntity cart) {
        cart.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        cartService.insert(cart);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CartEntity cart) {
        cartService.updateById(cart);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        cartService.deleteBatchIds(Arrays.asList(ids));
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
            if (map.get(RE_MIND_START) != null) {
                int remindStart = Integer.parseInt(map.get(RE_MIND_START).toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put(RE_MIND_START, sdf.format(remindStartDate));
            }
            if (map.get(RE_MIND_END) != null) {
                int remindEnd = Integer.parseInt(map.get(RE_MIND_END).toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put(RE_MIND_END, sdf.format(remindEndDate));
            }
        }

        Wrapper<CartEntity> wrapper = new EntityWrapper<>();
        if (map.get(RE_MIND_START) != null) {
            wrapper.ge(columnName, map.get(RE_MIND_START));
        }
        if (map.get(RE_MIND_END) != null) {
            wrapper.le(columnName, map.get(RE_MIND_END));
        }
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            wrapper.eq("userid", request.getSession().getAttribute(USER_ID));
        }


        int count = cartService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
