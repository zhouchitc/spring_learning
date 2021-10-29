package com.chi.config;

import com.chi.domain.Pet;
import com.chi.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/10/29 10:23
 * @Version 1.0
 */
@Import({Pet.class, User.class})
@Configuration
public class AppConfiguration {

    @Bean("myUser")
    public User getUser(){
        return new User("myTest01", 20);
    }
}
