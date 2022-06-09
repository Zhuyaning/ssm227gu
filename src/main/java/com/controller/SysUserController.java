package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.SysUserEntity;
import com.entity.view.SysUserView;
import com.service.SysUserService;
import com.service.TokenService;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * 用户
 * 前台接口
 */
@RestController
@CrossOrigin
@RequestMapping("/yonghu")
public class SysUserController {

    private static final String USER_ACCOUNT = "yonghuzhanghao";
    private static final String USER = "yonghu";

    private final SysUserService sysUserService;

    private final TokenService tokenService;

    @Autowired
    public SysUserController(SysUserService sysUserService, TokenService tokenService) {
        this.sysUserService = sysUserService;
        this.tokenService = tokenService;
    }

    /**
     * 用户登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public Result login(String username, String password) throws NoSuchAlgorithmException {
        SysUserEntity user = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq(USER_ACCOUNT, username));
        if (null == user || !user.getMima().equals(Md5Util.getMd5String(password))) {
            return Result.error("账号或密码不正确!");
        }
        String token = tokenService.generateToken(user.getId(), username, USER, "用户");
        return Result.ok().put("token", token);
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public Result register(@RequestBody SysUserEntity sysUser) {
        SysUserEntity user = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq(USER_ACCOUNT, sysUser.getYonghuzhanghao()));
        if (user != null) {
            return Result.error("注册用户已存在!");
        }
        sysUser.setId(CommonUtil.getSnowFlakeString());
        sysUser.setMima(Md5Util.getMd5String(sysUser.getMima()));
        sysUserService.insert(sysUser);
        return Result.ok();
    }

    /**
     * 退出
     */
    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Result.ok("退出成功");
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public Result getCurrUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        SysUserEntity user = sysUserService.selectById(id);
        return Result.ok().put("data", user);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public Result resetPass(String username) {
        SysUserEntity user = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq(USER_ACCOUNT, username));
        if (user == null) {
            return Result.error("账号不存在!");
        }
        user.setMima(Md5Util.getMd5String("123456"));
        sysUserService.updateById(user);
        return Result.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, SysUserEntity sysUser) {

        EntityWrapper<SysUserEntity> ew = new EntityWrapper<>();
        PageUtils page = sysUserService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, sysUser), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params, SysUserEntity sysUser) {
        EntityWrapper<SysUserEntity> entityWrapper = new EntityWrapper<>();
        PageUtils page = sysUserService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(entityWrapper, sysUser), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Result list(SysUserEntity sysUser) {
        EntityWrapper<SysUserEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(sysUser, USER));
        return Result.ok().put("data", sysUserService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Result query(SysUserEntity sysUser) {
        EntityWrapper<SysUserEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(sysUser, USER));
        SysUserView userView = sysUserService.selectView(ew);
        return Result.ok("查询用户成功").put("data", userView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        SysUserEntity user = sysUserService.selectById(id);
        return Result.ok().put("data", user);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        SysUserEntity sysUser = sysUserService.selectById(id);
        return Result.ok().put("data", sysUser);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysUserEntity sysUser) {
        sysUser.setId((long) (new Date().getTime() + Math.floor(Math.random() * 1000)));
        SysUserEntity user = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq(USER_ACCOUNT, sysUser.getYonghuzhanghao()));
        if (user != null) {
            return Result.error("用户已存在");
        }

        sysUser.setId(new Date().getTime());
        sysUserService.insert(sysUser);
        return Result.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Result add(@RequestBody SysUserEntity sysUser) {
        sysUser.setId((long) (new Date().getTime() + Math.floor(Math.random() * 1000)));
        SysUserEntity user = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq(USER_ACCOUNT, sysUser.getYonghuzhanghao()));
        if (user != null) {
            return Result.error("用户已存在!");
        }

        sysUser.setId(new Date().getTime());
        sysUserService.insert(sysUser);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysUserEntity sysUser) {
        sysUserService.updateById(sysUser);//全部更新
        return Result.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        sysUserService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<SysUserEntity> wrapper = new EntityWrapper<>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = sysUserService.selectCount(wrapper);
        return Result.ok().put("count", count);
    }
}
