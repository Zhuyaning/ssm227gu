package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ProductTypeEntity;
import com.entity.view.ProductTypeView;
import com.entity.vo.ProductTypeVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 商品分类
 *
 */
public interface ProductTypeService extends IService<ProductTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProductTypeVO> selectListVO(Wrapper<ProductTypeEntity> wrapper);

    ProductTypeVO selectVO(@Param("ew") Wrapper<ProductTypeEntity> wrapper);

    List<ProductTypeView> selectListView(Wrapper<ProductTypeEntity> wrapper);

    ProductTypeView selectView(@Param("ew") Wrapper<ProductTypeEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ProductTypeEntity> wrapper);

}

