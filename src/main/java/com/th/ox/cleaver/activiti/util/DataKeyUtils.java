package com.th.ox.cleaver.activiti.util;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * 返回数据字段处理助手类
 *
 * @author ZColin
 */
@Slf4j
public class DataKeyUtils {

    /**
     * 遍历移除集合或者对象中属性字段， 支持list、map、object。其他类型如果用到再增加
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void removeKey(Object bean, String... keys) {
        if (bean == null || keys == null || keys.length == 0) {
            return;
        }

        //不处理基本类型
        if (bean instanceof String || bean instanceof Short || bean instanceof Integer || bean instanceof Long || bean instanceof Char || bean instanceof 
                Float || bean instanceof Double || bean instanceof Boolean) {
            return;
        }

        //如果对象时集合类,进行值递归
        if (bean instanceof Collection) {
            ((Collection) bean).forEach(map -> removeKey(map, keys));
            return;
        } else if (bean instanceof Map) {
            //移除map本身的key
            for (String key : keys) {
                ((Map) bean).remove(key);
            }

            Set<Map.Entry> entries = ((Map) bean).entrySet();
            for (Map.Entry entry : entries) {
                removeKey(entry.getValue(), keys);
            }
            return;
        }

        //获取所有对象成员变量
        Field[] fields = bean.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            //跳过静态字段
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            String key = field.getName();
            try {
                field.setAccessible(true);

                //成员变量匹配，则将成员变量值设置为null
                for (String s : keys) {
                    if (key.equals(s)) {
                        field.set(field, null);
                    }
                }

                Object object = field.get(bean);
                if (object != null) {
                    removeKey(object, keys);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.info("removeKey", e);
            }
        }
    }
}
