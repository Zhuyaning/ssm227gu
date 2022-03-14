package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.DiscussshangpinxinxiEntity;
import com.entity.view.DiscussshangpinxinxiView;
import com.entity.vo.DiscussshangpinxinxiVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品信息评论表
 *
 */
public interface DiscussshangpinxinxiDao extends BaseMapper<DiscussshangpinxinxiEntity> {

    List<DiscussshangpinxinxiVO> selectListVO(@Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

    DiscussshangpinxinxiVO selectVO(@Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

    List<DiscussshangpinxinxiView> selectListView(@Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

    List<DiscussshangpinxinxiView> selectListView(Pagination page, @Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

    DiscussshangpinxinxiView selectView(@Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

}
