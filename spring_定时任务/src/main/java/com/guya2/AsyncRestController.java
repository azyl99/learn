package com.guya2;

/**
 * @author guya on 2018/9/20
 */

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Asyncrest: AsyncRestTemplate 异步发生请求测试
 *
 * @author mlxs
 * @since 2016/8/4
 */
@Controller
@RequestMapping("/async")
public class AsyncRestController {
    Logger logger = Logger.getLogger(AsyncRestController.class);

    @RequestMapping("/fivetime")
    @ResponseBody
    public String tenTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "5秒...";
    }

    /**
     * 同步调用
     *
     * @return
     */
    @RequestMapping("/sync")
    @ResponseBody
    public String sync() {
        //同步调用
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/async/fivetime";//休眠5秒的服务
        String forObject = template.getForObject(url, String.class);
        return "同步调用结束, 结果：" + forObject;
    }

    /**
     * 异步调用
     *
     * @return
     */
    @RequestMapping("/async")
    @ResponseBody
    public String async() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        String url = "http://localhost:8080/async/fivetime";//休眠5秒的服务
        //调用完后立即返回（没有阻塞）
        ListenableFuture<ResponseEntity<String>> forEntity = template.getForEntity(url, String.class);
        //异步调用后的回调函数
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            //调用失败
            @Override
            public void onFailure(Throwable ex) {
                logger.error("=====rest response faliure======");
            }

            //调用成功
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                logger.info("--->async rest response success----, result = " + result.getBody());
            }
        });
        return "异步调用结束";
    }


}
