package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ProductReviewEntity;
import com.entity.view.ProductReviewView;
import com.entity.vo.ProductReviewVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 商品信息评论表
 */
public interface ProductReviewService extends IService<ProductReviewEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProductReviewVO> selectListVO(Wrapper<ProductReviewEntity> wrapper);

    ProductReviewVO selectVO(@Param("ew") Wrapper<ProductReviewEntity> wrapper);

    List<ProductReviewView> selectListView(Wrapper<ProductReviewEntity> wrapper);

    ProductReviewView selectView(@Param("ew") Wrapper<ProductReviewEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ProductReviewEntity> wrapper);

}

