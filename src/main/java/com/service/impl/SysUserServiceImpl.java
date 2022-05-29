package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.SysUserDao;
import com.entity.SysUserEntity;
import com.entity.view.SysUserView;
import com.entity.vo.SysUserVO;
import com.service.SysUserService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("yonghuService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysUserEntity> page = this.selectPage(
                new Query<SysUserEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<SysUserEntity> wrapper) {
        Page<SysUserView> page = new Query<SysUserView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<SysUserVO> selectListVO(Wrapper<SysUserEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public SysUserVO selectVO(Wrapper<SysUserEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<SysUserView> selectListView(Wrapper<SysUserEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public SysUserView selectView(Wrapper<SysUserEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
