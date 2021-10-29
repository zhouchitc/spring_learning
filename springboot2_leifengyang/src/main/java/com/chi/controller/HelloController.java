package com.chi.controller;

import com.chi.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/10/29 14:59
 * @Version 1.0
 */
@RestController("/helloController")
public class HelloController {

    @Autowired
    private Car car;

    @RequestMapping("/getCar")
    public Car getcar(){
        return car;
    }
}
