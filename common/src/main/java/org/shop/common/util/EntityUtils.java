package org.shop.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityUtils {
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyNotNullProperties(Object source,Object target){
        BeanUtils.copyProperties(source, target,getNullPropertyNames(source));
    }
    public static boolean containsFieldWithName(Class clazz, String fieldName){
        if(fieldName == null || fieldName.trim().equals(""))
            return false;
        Field[] declaredFields = clazz.getDeclaredFields();
        return List.of(declaredFields).stream().anyMatch(field -> fieldName.equals(field.getName()));
    }

}