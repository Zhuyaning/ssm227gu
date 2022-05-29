package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ProductTypeDao;
import com.entity.ProductTypeEntity;
import com.entity.view.ProductTypeView;
import com.entity.vo.ProductTypeVO;
import com.service.ProductTypeService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shangpinfenleiService")
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeDao, ProductTypeEntity> implements ProductTypeService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductTypeEntity> page = this.selectPage(
                new Query<ProductTypeEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ProductTypeEntity> wrapper) {
        Page<ProductTypeView> page = new Query<ProductTypeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<ProductTypeVO> selectListVO(Wrapper<ProductTypeEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ProductTypeVO selectVO(Wrapper<ProductTypeEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ProductTypeView> selectListView(Wrapper<ProductTypeEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ProductTypeView selectView(Wrapper<ProductTypeEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
