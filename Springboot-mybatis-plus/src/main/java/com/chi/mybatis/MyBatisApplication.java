package com.chi.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chi
 * @Description: TODO
 * @date 2022/6/19 15:06
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan({"com.chi.mybatis.mapper", "com.gitee.sunchenbin.mybatis.actable.dao.*"})
@ComponentScan(basePackages = {"com.chi.mybatis", "com.gitee.sunchenbin.mybatis.actable.manager.*"})
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class);
    }
}
