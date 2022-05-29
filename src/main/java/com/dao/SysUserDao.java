package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.SysUserEntity;
import com.entity.view.SysUserView;
import com.entity.vo.SysUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户
 *
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    List<SysUserVO> selectListVO(@Param("ew") Wrapper<SysUserEntity> wrapper);

    SysUserVO selectVO(@Param("ew") Wrapper<SysUserEntity> wrapper);

    List<SysUserView> selectListView(@Param("ew") Wrapper<SysUserEntity> wrapper);

    List<SysUserView> selectListView(Pagination page, @Param("ew") Wrapper<SysUserEntity> wrapper);

    SysUserView selectView(@Param("ew") Wrapper<SysUserEntity> wrapper);

}
