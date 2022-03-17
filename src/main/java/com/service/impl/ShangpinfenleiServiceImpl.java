package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ShangpinfenleiDao;
import com.entity.ShangpinfenleiEntity;
import com.entity.view.ShangpinfenleiView;
import com.entity.vo.ShangpinfenleiVO;
import com.service.ShangpinfenleiService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shangpinfenleiService")
public class ShangpinfenleiServiceImpl extends ServiceImpl<ShangpinfenleiDao, ShangpinfenleiEntity> implements ShangpinfenleiService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangpinfenleiEntity> page = this.selectPage(
                new Query<ShangpinfenleiEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinfenleiEntity> wrapper) {
        Page<ShangpinfenleiView> page = new Query<ShangpinfenleiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<ShangpinfenleiVO> selectListVO(Wrapper<ShangpinfenleiEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ShangpinfenleiVO selectVO(Wrapper<ShangpinfenleiEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ShangpinfenleiView> selectListView(Wrapper<ShangpinfenleiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ShangpinfenleiView selectView(Wrapper<ShangpinfenleiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
