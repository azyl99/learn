package com.zoo.mvc.framework.utils;

/**
 * @author guya on 2018/11/30
 */
public final class BeanUtil {

    public static String getDefaultBeanName(Class<?> clazz) {
        String className = clazz.getSimpleName();
        byte[] bytes = className.getBytes();
        bytes[0] |= 0x20;
        return new String(bytes);
    }
}
