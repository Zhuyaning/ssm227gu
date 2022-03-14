package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.OrdersDao;
import com.entity.OrdersEntity;
import com.entity.view.OrdersView;
import com.entity.vo.OrdersVO;
import com.service.OrdersService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrdersEntity> page = this.selectPage(
                new Query<OrdersEntity>(params).getPage(),
                new EntityWrapper<OrdersEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<OrdersEntity> wrapper) {
        Page<OrdersView> page = new Query<OrdersView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<OrdersVO> selectListVO(Wrapper<OrdersEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public OrdersVO selectVO(Wrapper<OrdersEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<OrdersView> selectListView(Wrapper<OrdersEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public OrdersView selectView(Wrapper<OrdersEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
