package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ProductTypeEntity;
import com.entity.view.ProductTypeView;
import com.entity.vo.ProductTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品分类
 */
public interface ProductTypeDao extends BaseMapper<ProductTypeEntity> {

    List<ProductTypeVO> selectListVO(@Param("ew") Wrapper<ProductTypeEntity> wrapper);

    ProductTypeVO selectVO(@Param("ew") Wrapper<ProductTypeEntity> wrapper);

    List<ProductTypeView> selectListView(@Param("ew") Wrapper<ProductTypeEntity> wrapper);

    List<ProductTypeView> selectListView(Pagination page, @Param("ew") Wrapper<ProductTypeEntity> wrapper);

    ProductTypeView selectView(@Param("ew") Wrapper<ProductTypeEntity> wrapper);

}
