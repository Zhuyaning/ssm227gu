package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ProductInfoEntity;
import com.entity.view.ProductInfoView;
import com.entity.vo.ProductInfoVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 商品信息
 *
 */
public interface ProductInfoService extends IService<ProductInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProductInfoVO> selectListVO(Wrapper<ProductInfoEntity> wrapper);

    ProductInfoVO selectVO(@Param("ew") Wrapper<ProductInfoEntity> wrapper);

    List<ProductInfoView> selectListView(Wrapper<ProductInfoEntity> wrapper);

    ProductInfoView selectView(@Param("ew") Wrapper<ProductInfoEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ProductInfoEntity> wrapper);

}

