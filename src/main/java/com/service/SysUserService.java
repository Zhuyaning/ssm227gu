package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.SysUserEntity;
import com.entity.view.SysUserView;
import com.entity.vo.SysUserVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 用户
 *
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysUserVO> selectListVO(Wrapper<SysUserEntity> wrapper);

    SysUserVO selectVO(@Param("ew") Wrapper<SysUserEntity> wrapper);

    List<SysUserView> selectListView(Wrapper<SysUserEntity> wrapper);

    SysUserView selectView(@Param("ew") Wrapper<SysUserEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<SysUserEntity> wrapper);

}

