package com.imooc.user.controller;

import com.imooc.api.controller.user.UserControllerApi;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "helloBack")
public class HelloController implements UserControllerApi {

    @Value("${server.port}")
    private String port;

    @HystrixCommand//(fallbackMethod = "helloBack")
    @Override
    public String hello(String name) {

//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }
        if (name.equals("a")) {
            throw new RuntimeException("全局降级");
        }


        System.out.println("name: " + name);
        return "hello eureka";
    }

    public String helloBack() {

        System.out.println("name: defaultFallBack");
        return "hello Hystrix";
    }
}
