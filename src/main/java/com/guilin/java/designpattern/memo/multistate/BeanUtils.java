package com.guilin.java.designpattern.memo.multistate;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/9/9 0009.
 */
public class BeanUtils {

    //把bean的所有属性及值备份到map中
    public static Map<String, Object> backProp(Object bean) {
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            //获得属性描述
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String fieldName = descriptor.getName();
                Method readMethod = descriptor.getReadMethod();
                Object value = readMethod.invoke(bean);
                if (!fieldName.equalsIgnoreCase("class")) {
                    map.put(fieldName, value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //将map中的键值对设置到bean的属性中
    public static void restoreProp(Object bean, Map<String, Object> propMap) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String fieldName = descriptor.getName();
                //如果有这个属性
                if (propMap.containsKey(fieldName)) {
                    Method setMethod = descriptor.getWriteMethod();
                    setMethod.invoke(bean, new Object[]{propMap.get(fieldName)});
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
