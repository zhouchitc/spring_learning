package com.chi.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chi
 * @Description:
 *
 * 方法1:
 * @Component
 * @ConfigurationProperties(prefix = "mycar")
 *
 * 方法2:
 * @EnableConfigurationProperties(Car.class)
 * @ConfigurationProperties(prefix = "mycar")
 *
 *
 * @date 2021/10/29 14:56
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {

    private String name;
    private Integer price;

    public Car() {
    }

    public Car(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
