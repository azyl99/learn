package com.guya2.todownload;

import groovy.json.JsonOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author guya on 2020/2/16
 */
@Slf4j
@Controller
@RequestMapping(value = "/to_download", method = RequestMethod.GET)
public class ToDownloadController {
    private final AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

    @RequestMapping("/txt")
    @ResponseBody
    public String toDownloadTxt() throws InterruptedException, ExecutionException, TimeoutException {
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.exchange(
                "http://127.0.0.1:8080/for_download/txt",
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                String.class);
        ResponseEntity<String> responseEntity = future.get(3000L, TimeUnit.MILLISECONDS);
        log.info(JsonOutput.toJson(responseEntity));
        return responseEntity.getBody();
    }
}
