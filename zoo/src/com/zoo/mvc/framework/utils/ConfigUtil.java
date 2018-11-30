package com.zoo.mvc.framework.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @author guya on 2018/11/30
 */
public final class ConfigUtil {

    /**
     *
     * @param contextConfigLocation
     */
    public static void doLoadConfig(ClassLoader classLoader, Properties properties, String contextConfigLocation) {
        InputStream is = classLoader.getResourceAsStream(contextConfigLocation);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void doScanner(ClassLoader classLoader, List<String> classNames, String scanPackage) {
        String dir = scanPackage.replace(".", "/");
        URL url = classLoader.getResource("/" + dir);
        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(classLoader, classNames, scanPackage + '.' + file.getName());
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = scanPackage + "." + file.getName().replace(".class", "");
                    classNames.add(className);
                }
            }
        }
    }
}
