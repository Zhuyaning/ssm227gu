package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.SysUserEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 用户
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 */
@TableName("yonghu")
public class SysUserView extends SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public SysUserView() {
    }

    public SysUserView(SysUserEntity sysUserEntity) {
        try {
            BeanUtils.copyProperties(this, sysUserEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
