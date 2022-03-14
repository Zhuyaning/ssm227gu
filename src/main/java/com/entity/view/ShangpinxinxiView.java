package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.ShangpinxinxiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 商品信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shangpinxinxi")
public class ShangpinxinxiView extends ShangpinxinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ShangpinxinxiView() {
    }

    public ShangpinxinxiView(ShangpinxinxiEntity shangpinxinxiEntity) {
        try {
            BeanUtils.copyProperties(this, shangpinxinxiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
