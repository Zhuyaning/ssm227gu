package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.CartEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 购物车表
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("cart")
public class CartView extends CartEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public CartView() {
    }

    public CartView(CartEntity cartEntity) {
        try {
            BeanUtils.copyProperties(this, cartEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {

            e.printStackTrace();
        }

    }
}
