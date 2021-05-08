package com.imooc.article.controller;

import com.imooc.api.controller.article.ArticleControllerApi;
import com.imooc.api.controller.user.UserControllerApi;
import com.imooc.article.stream.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
public class HelloController implements ArticleControllerApi {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserControllerApi userControllerApi;

    @Autowired
    private StreamService streamService;

    @Override
    public String hello(String name) {

        String serviceId = "SERVICE-USER";
//        List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
//        ServiceInstance userInstance = instanceList.get(0);
//
//        String userServiceUrl = "http://" + userInstance.getHost() + ":" + userInstance.getPort() + "/" + "hello";
//        String userServiceUrl = "http://" + serviceId + "/" + "hello";
//
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(userServiceUrl, String.class);
//
//
//        return responseEntity.toString();
//        String name = "feign";

        return userControllerApi.hello(name);
    }

    @RequestMapping("stream")
    public String stream() {
        streamService.sendMsg();
        return "OK....";
    }

}
