package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ProductInfoEntity;
import com.entity.view.ProductInfoView;
import com.entity.vo.ProductInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品信息
 *
 */
public interface ProductInfoDao extends BaseMapper<ProductInfoEntity> {

    List<ProductInfoVO> selectListVO(@Param("ew") Wrapper<ProductInfoEntity> wrapper);

    ProductInfoVO selectVO(@Param("ew") Wrapper<ProductInfoEntity> wrapper);

    List<ProductInfoView> selectListView(@Param("ew") Wrapper<ProductInfoEntity> wrapper);

    List<ProductInfoView> selectListView(Pagination page, @Param("ew") Wrapper<ProductInfoEntity> wrapper);

    ProductInfoView selectView(@Param("ew") Wrapper<ProductInfoEntity> wrapper);

}
