package com.imooc.api.controller.user;

import com.imooc.api.config.MyServiceList;
import com.imooc.api.controller.user.fallback.UserControllerFactoryFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "user")
@FeignClient(value = MyServiceList.SERVICE_USER, fallbackFactory=UserControllerFactoryFallback.class)
public interface UserControllerApi {

    
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name);


}
