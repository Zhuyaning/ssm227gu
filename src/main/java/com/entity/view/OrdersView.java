package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.OrdersEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("orders")
public class OrdersView extends OrdersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public OrdersView() {
    }

    public OrdersView(OrdersEntity ordersEntity) {
        try {
            BeanUtils.copyProperties(this, ordersEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
