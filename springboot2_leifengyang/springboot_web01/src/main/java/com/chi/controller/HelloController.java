package com.chi.controller;

import com.chi.service.IAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/11/1 15:24
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Resource
    private IAsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(@RequestParam("username") String name){
        return "hello";
    }

    @RequestMapping("/async")
    public void async(){
//        try {
//            asyncService.process1();
//            asyncService.process2();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("开始");
        String process = asyncService.process();
        System.out.println("结束: " + process);
    }
}
