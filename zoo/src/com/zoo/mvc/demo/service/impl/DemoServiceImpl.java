package com.zoo.mvc.demo.service.impl;

import com.zoo.mvc.demo.service.DemoService;
import com.zoo.mvc.framework.annotations.ZooService;

/**
 * @author guya on 2018/11/29
 */
@ZooService
public class DemoServiceImpl implements DemoService {
    @Override
    public String say(String name) {
        return "say " + name;
    }
}
