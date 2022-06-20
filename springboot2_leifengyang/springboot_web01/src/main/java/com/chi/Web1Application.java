package com.chi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/11/1 09:05
 * @Version 1.0
 */
@SpringBootApplication
@EnableAsync
public class Web1Application {
    public static void main(String[] args) {
        SpringApplication.run(Web1Application.class);
    }
}
