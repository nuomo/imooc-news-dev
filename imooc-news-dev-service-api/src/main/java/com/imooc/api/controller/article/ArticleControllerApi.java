package com.imooc.api.controller.article;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "article")
public interface ArticleControllerApi {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam String name);
}
