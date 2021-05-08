package com.imooc.user.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MyStreamChannel.class)
public class MyStreamConsumer {

    @StreamListener(MyStreamChannel.INPUT)
    public void receiveMsg(String msg) {
        System.out.println(msg);
    }
}
