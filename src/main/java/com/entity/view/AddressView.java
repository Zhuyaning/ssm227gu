package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.AddressEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 地址
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 */
@TableName("address")
public class AddressView extends AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public AddressView() {
    }

    public AddressView(AddressEntity addressEntity) {
        try {
            BeanUtils.copyProperties(this, addressEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
