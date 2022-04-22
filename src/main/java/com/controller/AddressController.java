package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.AddressEntity;
import com.entity.view.AddressView;
import com.service.AddressService;
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
 * 地址
 * 后端接口
 */
@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, AddressEntity address,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            address.setUserid((Long) request.getSession().getAttribute("userId"));
        }

        EntityWrapper<AddressEntity> ew = new EntityWrapper<>();
        PageUtils page = addressService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, address), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, AddressEntity address,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            address.setUserid((Long) request.getSession().getAttribute("userId"));
        }

        EntityWrapper<AddressEntity> ew = new EntityWrapper<>();
        PageUtils page = addressService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, address), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(AddressEntity address) {
        EntityWrapper<AddressEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(address, "address"));
        return Result.ok().put("data", addressService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(AddressEntity address) {
        EntityWrapper<AddressEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(address, "address"));
        AddressView addressView = addressService.selectView(ew);
        return Result.ok("查询地址成功").put("data", addressView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        AddressEntity address = addressService.selectById(id);
        return Result.ok().put("data", address);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        AddressEntity address = addressService.selectById(id);
        return Result.ok().put("data", address);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody AddressEntity address, HttpServletRequest request) {
        address.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(address);
        address.setUserid((Long) request.getSession().getAttribute("userId"));
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (address.getIsdefault().equals("是")) {
            addressService.updateForSet("isdefault='否'", new EntityWrapper<AddressEntity>().eq("userid", userId));
        }
        address.setUserid(userId);

        addressService.insert(address);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody AddressEntity address, HttpServletRequest request) {
        address.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(address);
        address.setUserid((Long) request.getSession().getAttribute("userId"));
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (address.getIsdefault().equals("是")) {
            addressService.updateForSet("isdefault='否'", new EntityWrapper<AddressEntity>().eq("userid", userId));
        }
        address.setUserid(userId);

        addressService.insert(address);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody AddressEntity address, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(address);
        if (address.getIsdefault().equals("是")) {
            addressService.updateForSet("isdefault='否'", new EntityWrapper<AddressEntity>().eq("userid", request.getSession().getAttribute("userId")));
        }
        addressService.updateById(address);//全部更新
        return Result.ok();
    }

    /**
     * 获取默认地址
     */
    @RequestMapping("/default")
    public Result defaultAddress(HttpServletRequest request) {
        Wrapper<AddressEntity> wrapper = new EntityWrapper<AddressEntity>().eq("isdefault", "是").eq("userid", request.getSession().getAttribute("userId"));
        AddressEntity address = addressService.selectOne(wrapper);
        return Result.ok().put("data", address);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        addressService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<AddressEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            wrapper.eq("userid", request.getSession().getAttribute("userId"));
        }


        int count = addressService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }


}
