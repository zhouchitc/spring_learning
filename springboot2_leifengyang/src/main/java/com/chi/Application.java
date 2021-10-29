package com.chi;

import com.chi.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/10/29 10:20
 * @Version 1.0
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);

        String[] beanDefinitionNames = run.getBeanDefinitionNames();

        System.out.println("------所有IOC容器内的bean------");
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        /**
         *------所有IOC容器内的User bean------
         * com.chi.domain.User
         * myUser
         *
         * 使用@Bean注解的,会返回@Bean中的名字,或者方法名
         * 使用@Import注解的时候,会返回全类名
         */
        System.out.println("------所有IOC容器内的User bean------");
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

    }
}
