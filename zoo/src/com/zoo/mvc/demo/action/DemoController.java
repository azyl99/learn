package com.zoo.mvc.demo.action;

import com.zoo.mvc.demo.service.DemoService;
import com.zoo.mvc.framework.annotations.ZooAutowire;
import com.zoo.mvc.framework.annotations.ZooController;
import com.zoo.mvc.framework.annotations.ZooRequestMapping;
import com.zoo.mvc.framework.annotations.ZooRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author guya on 2018/11/29
 */
@ZooRequestMapping("/demo")
@ZooController
public class DemoController {

    @ZooAutowire
    private DemoService demoService;

    @ZooRequestMapping("/say")
    public void say(HttpServletRequest req, HttpServletResponse resp,
                    @ZooRequestParam("name") String name) {
        String result = demoService.say(name);

        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
