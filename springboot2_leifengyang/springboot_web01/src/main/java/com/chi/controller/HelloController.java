package com.chi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/11/1 15:24
 * @Version 1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestParam("username") String name){
        return "hello";
    }
}
