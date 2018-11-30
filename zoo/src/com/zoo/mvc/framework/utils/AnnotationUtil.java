package com.zoo.mvc.framework.utils;

import com.zoo.mvc.framework.annotations.ZooComponent;
import com.zoo.mvc.framework.annotations.ZooController;
import com.zoo.mvc.framework.annotations.ZooService;

/**
 * @author guya on 2018/11/30
 */
public final class AnnotationUtil {

    private static Class<?>[] componentAnnotations = {ZooComponent.class, ZooController.class, ZooService.class};
    /**
     * 判断一个注解是否是Component
     *
     * @param annotationClazz
     * @return
     */
    public static boolean getComponentAnnotation(Class<?> annotationClazz) {
        for (Class<?> componentAnnotation : componentAnnotations) {
            if (componentAnnotation == annotationClazz) {
                return true;
            }
        }
        return false;
    }
}
