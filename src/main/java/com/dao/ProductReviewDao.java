package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ProductReviewEntity;
import com.entity.view.ProductReviewView;
import com.entity.vo.ProductReviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品信息评论表
 *
 */
public interface ProductReviewDao extends BaseMapper<ProductReviewEntity> {

    List<ProductReviewVO> selectListVO(@Param("ew") Wrapper<ProductReviewEntity> wrapper);

    ProductReviewVO selectVO(@Param("ew") Wrapper<ProductReviewEntity> wrapper);

    List<ProductReviewView> selectListView(@Param("ew") Wrapper<ProductReviewEntity> wrapper);

    List<ProductReviewView> selectListView(Pagination page, @Param("ew") Wrapper<ProductReviewEntity> wrapper);

    ProductReviewView selectView(@Param("ew") Wrapper<ProductReviewEntity> wrapper);

}
