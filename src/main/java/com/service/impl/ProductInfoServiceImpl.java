package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ProductInfoDao;
import com.entity.ProductInfoEntity;
import com.entity.view.ProductInfoView;
import com.entity.vo.ProductInfoVO;
import com.service.ProductInfoService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shangpinxinxiService")
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfoEntity> implements ProductInfoService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductInfoEntity> page = this.selectPage(
                new Query<ProductInfoEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ProductInfoEntity> wrapper) {
        Page<ProductInfoView> page = new Query<ProductInfoView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<ProductInfoVO> selectListVO(Wrapper<ProductInfoEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ProductInfoVO selectVO(Wrapper<ProductInfoEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ProductInfoView> selectListView(Wrapper<ProductInfoEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ProductInfoView selectView(Wrapper<ProductInfoEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
