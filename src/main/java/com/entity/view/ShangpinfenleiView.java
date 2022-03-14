package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.ShangpinfenleiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 商品分类
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 */
@TableName("shangpinfenlei")
public class ShangpinfenleiView extends ShangpinfenleiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ShangpinfenleiView() {
    }

    public ShangpinfenleiView(ShangpinfenleiEntity shangpinfenleiEntity) {
        try {
            BeanUtils.copyProperties(this, shangpinfenleiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
