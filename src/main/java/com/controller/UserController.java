package com.controller;


import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.UserEntity;
import com.service.TokenService;
import com.service.UserService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 管理员登录相关
 */
@RequestMapping("users")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping(value = "/login")
    public Result login(@RequestBody UserEntity userEntity) {
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", userEntity.getUsername()));
        if (user == null || !user.getPassword().equals(userEntity.getPassword())) {
            return Result.error("账号或密码不正确");
        }
        String token = tokenService.generateToken(user.getId(), userEntity.getUsername(), "users", user.getRole());
        return Result.ok().put("token", token);
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public Result register(@RequestBody UserEntity user) {
        if (userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername())) != null) {
            return Result.error("用户已存在");
        }
        userService.insert(user);
        return Result.ok();
    }

    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Result.ok("退出成功");
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public Result resetPass(String username) {
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
        if (user == null) {
            return Result.error("账号不存在");
        }
        user.setPassword("123456");
        userService.update(user, null);
        return Result.ok("密码已重置为：123456");
    }

    /**
     * 列表
     */
    @RequestMapping("/page")
    public Result page(@RequestParam Map<String, Object> params, UserEntity user) {
        EntityWrapper<UserEntity> ew = new EntityWrapper<>();
        PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return Result.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(UserEntity user) {
        EntityWrapper<UserEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(user, "user"));
        return Result.ok().put("data", userService.selectListView(ew));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        UserEntity user = userService.selectById(id);
        return Result.ok().put("data", user);
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public Result getCurrUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        UserEntity user = userService.selectById(id);
        return Result.ok().put("data", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody UserEntity user) {
        if (userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername())) != null) {
            return Result.error("用户已存在");
        }
        userService.insert(user);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody UserEntity user) {
        UserEntity u = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername()));
        if (u != null && !u.getId().equals(user.getId()) && u.getUsername().equals(user.getUsername())) {
            return Result.error("用户名已存在。");
        }
        userService.updateById(user);//全部更新
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        userService.deleteBatchIds(Arrays.asList(ids));
        return Result.ok();
    }
}
