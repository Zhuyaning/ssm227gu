package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.CartDao;
import com.entity.CartEntity;
import com.entity.view.CartView;
import com.entity.vo.CartVO;
import com.service.CartService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartDao, CartEntity> implements CartService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CartEntity> page = this.selectPage(
                new Query<CartEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<CartEntity> wrapper) {
        Page<CartView> page = new Query<CartView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<CartVO> selectListVO(Wrapper<CartEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public CartVO selectVO(Wrapper<CartEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<CartView> selectListView(Wrapper<CartEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public CartView selectView(Wrapper<CartEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
