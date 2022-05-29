package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ProductReviewDao;
import com.entity.ProductReviewEntity;
import com.entity.view.ProductReviewView;
import com.entity.vo.ProductReviewVO;
import com.service.ProductReviewService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("discussshangpinxinxiService")
public class ProductReviewServiceImpl extends ServiceImpl<ProductReviewDao, ProductReviewEntity> implements ProductReviewService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductReviewEntity> page = this.selectPage(
                new Query<ProductReviewEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ProductReviewEntity> wrapper) {
        Page<ProductReviewView> page = new Query<ProductReviewView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        return new PageUtils(page);
    }

    @Override
    public List<ProductReviewVO> selectListVO(Wrapper<ProductReviewEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ProductReviewVO selectVO(Wrapper<ProductReviewEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ProductReviewView> selectListView(Wrapper<ProductReviewEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ProductReviewView selectView(Wrapper<ProductReviewEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
