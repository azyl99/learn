package com.zoo.mvc.framework;

import com.zoo.mvc.framework.utils.BeanUtil;
import com.zoo.mvc.framework.utils.ConfigUtil;
import com.zoo.mvc.framework.annotations.ZooController;
import com.zoo.mvc.framework.annotations.ZooService;
import com.zoo.mvc.framework.utils.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author guya on 2018/11/29
 */
public class ZooMvcServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String, Object> ioc = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 1. 加载配置文件。只有url第一次访问到这个servlet时才会init
        // <init-param>
        //     <param-name>contextConfigLocation</param-name>
        //     <param-value>application.properties</param-value>
        // </init-param>
        ConfigUtil.doLoadConfig(this.getClass().getClassLoader(), contextConfig, config.getInitParameter("contextConfigLocation"));

        // 2. 解析配置文件，扫描相关的类
        // TODO: 使用@ComponentScan注解
        ConfigUtil.doScanner(this.getClass().getClassLoader(), classNames, contextConfig.getProperty("scanPackage"));

        // 3. 初始化所有的类，放入IOC容器中
        // Inversion of Control，缩写为IoC。由IoC容器来管理对象的生命周期、依赖关系等
        doInstance();

    }

    private void doInstance() {
        for (String className : classNames) {
            try {
                // 根据类名获取类
                // 区别：classloader.loadClass()不会执行静态方法
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(ZooService.class)) {
                    ZooService zooService = clazz.getAnnotation(ZooService.class);
                    String value = zooService.value();
                    if (StringUtil.isEmpty(value)) {
                        // 获取简单类名（去掉包名），然后首字母小写，得到默认的beanName
                        String beanName = BeanUtil.getDefaultBeanName(clazz);
                        Object object = clazz.newInstance();
                        ioc.put(beanName, object);
                    } else {

                    }

                } else if (clazz.isAnnotationPresent(ZooController.class)) {

                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        for (Map.Entry<String, Object> entry: ioc.entrySet()) {
//            String className = entry.getKey();
//            Object ins
//        }
    }
}
