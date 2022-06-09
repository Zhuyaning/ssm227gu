package com.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Mybatis-Plus工具类
 */
public class MPUtil {

    public static final char UNDERLINE = '_';


    //mybatis plus allEQ 表达式转换
    public static Map<String,Object> allEQMapPre(Object bean, String pre) {
        Map<String, Object> map = BeanUtil.beanToMap(bean);
        return camelToUnderlineMap(map, pre);
    }

    //mybatis plus allEQ 表达式转换
    public static Map<String,Object> allEQMap(Object bean) {
        Map<String, Object> map = BeanUtil.beanToMap(bean);
        return camelToUnderlineMap(map, "");
    }

    public static  Wrapper allLikePre(Wrapper<Object> wrapper, Object bean, String pre) {
        return genLike(wrapper, camelToUnderlineMap(BeanUtil.beanToMap(bean), pre));
    }

    public static Wrapper allLike(Wrapper wrapper, Object bean) {
        Map<String, Object> result = BeanUtil.beanToMap(bean, true, true);
        return genLike(wrapper, result);
    }


    public static Wrapper genLike(Wrapper wrapper, Map param) {
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i > 0) wrapper.and();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String value = (String) entry.getValue();
            wrapper.like(key, value);
            i++;
        }
        return wrapper;
    }

    public static Wrapper likeOrEq(Wrapper wrapper, Object bean) {
        Map<String, Object> result = BeanUtil.beanToMap(bean, true, true);
        return genLikeOrEq(wrapper, result);
    }

    public static Wrapper genLikeOrEq(Wrapper wrapper, Map param) {
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i > 0) wrapper.and();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            if (entry.getValue().toString().contains("%")) {
                wrapper.like(key, entry.getValue().toString().replace("%", ""));
            } else {
                wrapper.eq(key, entry.getValue());
            }
            i++;
        }
        return wrapper;
    }

    public static Wrapper allEq(Wrapper wrapper, Object bean) {
        Map<String, Object> result = BeanUtil.beanToMap(bean, true, true);
        return genEq(wrapper, result);
    }


    public static Wrapper genEq(Wrapper wrapper, Map param) {
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i > 0) wrapper.and();
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            wrapper.eq(key, entry.getValue());
            i++;
        }
        return wrapper;
    }


    public static Wrapper between(Wrapper wrapper, Map<String, Object> params) {
        for (String key : params.keySet()) {
            String columnName = "";
            if (key.endsWith("_start")) {
                columnName = key.substring(0, key.indexOf("_start"));
                if (StringUtils.isNotBlank(params.get(key).toString())) {
                    wrapper.ge(columnName, params.get(key));
                }
            }
            if (key.endsWith("_end")) {
                columnName = key.substring(0, key.indexOf("_end"));
                if (StringUtils.isNotBlank(params.get(key).toString())) {
                    wrapper.le(columnName, params.get(key));
                }
            }
        }
        return wrapper;
    }

    public static Wrapper sort(Wrapper wrapper, Map<String, Object> params) {
        String order = "";
        if (params.get("order") != null && StringUtils.isNotBlank(params.get("order").toString())) {
            order = params.get("order").toString();
        }
        if (params.get("sort") != null && StringUtils.isNotBlank(params.get("sort").toString())) {
            if (order.equalsIgnoreCase("desc")) {
                wrapper.orderDesc(Arrays.asList(params.get("sort")));
            } else {
                wrapper.orderAsc(Arrays.asList(params.get("sort")));
            }
        }
        return wrapper;
    }


    /**
     * 驼峰格式字符串转换为下划线格式字符串
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param.trim())) {
            return "";
        }
        int paramLength = param.length();
        StringBuilder res = new StringBuilder(paramLength);
        for (int i = 0; i < paramLength; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                res.append(UNDERLINE);
                res.append(Character.toLowerCase(c));
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] ages) {
        System.out.println(camelToUnderline("ABCddfANM"));
    }

    public static Map<String,Object> camelToUnderlineMap(Map<String,Object> param, String pre) {

        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            String newKey = camelToUnderline(key);
            if (pre.endsWith(".")) {
                newMap.put(pre + newKey, entry.getValue());
            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newKey, entry.getValue());
            } else {

                newMap.put(pre + "." + newKey, entry.getValue());
            }
        }
        return newMap;
    }
}
