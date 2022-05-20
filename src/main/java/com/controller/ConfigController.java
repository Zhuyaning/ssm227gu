
package com.controller;


import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.service.ConfigService;
import com.utils.PageUtils;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 登录相关
 */
@RequestMapping("config")
@RestController
@CrossOrigin
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<>();
        PageUtils page = configService.queryPage(params);
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<>();
        PageUtils page = configService.queryPage(params);
        return Result.ok().put("data", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        ConfigEntity config = configService.selectById(id);
        return Result.ok().put("data", config);
    }

    /**
     * 详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") String id) {
        ConfigEntity config = configService.selectById(id);
        return Result.ok().put("data", config);
    }

    /**
     * 根据name获取信息
     */
    @RequestMapping("/info")
    public Result infoByName(@RequestParam String name) {
        ConfigEntity config = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
        return Result.ok().put("data", config);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody ConfigEntity config) {
        configService.insert(config);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ConfigEntity config) {
        configService.updateById(config);//全部更新
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        configService.deleteBatchIds(Arrays.asList(ids));
        return Result.ok();
    }
}
