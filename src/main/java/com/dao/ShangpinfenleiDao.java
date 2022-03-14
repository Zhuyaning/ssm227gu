package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ShangpinfenleiEntity;
import com.entity.view.ShangpinfenleiView;
import com.entity.vo.ShangpinfenleiVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品分类
 */
public interface ShangpinfenleiDao extends BaseMapper<ShangpinfenleiEntity> {

    List<ShangpinfenleiVO> selectListVO(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    ShangpinfenleiVO selectVO(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    List<ShangpinfenleiView> selectListView(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    List<ShangpinfenleiView> selectListView(Pagination page, @Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    ShangpinfenleiView selectView(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

}
