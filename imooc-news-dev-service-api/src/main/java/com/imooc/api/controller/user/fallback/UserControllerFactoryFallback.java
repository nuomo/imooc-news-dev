package com.imooc.api.controller.user.fallback;

import com.imooc.api.controller.user.UserControllerApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserControllerFactoryFallback implements FallbackFactory<UserControllerApi> {
    @Override
    public UserControllerApi create(Throwable throwable) {
        return new UserControllerApi() {
            @Override
            public String hello(String name) {
                return "FactoryFallback ";
            }
        };
    }
}

