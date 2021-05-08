package com.imooc.zuul.controller;

import com.imooc.api.controller.user.UserControllerApi;
import com.imooc.utils.RedisOperator;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController  {

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/hello")
    public String hello() {
        return "hello zuul";
    }

    @RequestMapping("redis")
    public String redis() {
        return redis.get("age");
    }
}
