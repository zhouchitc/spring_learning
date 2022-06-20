package com.chi.service.impl;

import com.chi.config.SpringContext;
import com.chi.service.IAsyncService;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author chi
 * @Description: TODO
 * @date 2022/1/3 20:29
 * @Version 1.0
 */
@Service("asyncServiceImpl")
public class AsyncServiceImpl implements IAsyncService {

    @Override
    @Async
    public String process() {
        System.out.println("before process");
        try {
            IAsyncService asyncServiceImpl = SpringContext.getBean("asyncServiceImpl", IAsyncService.class);

            asyncServiceImpl.process1();
            asyncServiceImpl.process2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after process");

        return "test";
    }

//    @Async
    public void process2() throws InterruptedException {
        for (int i = 0; i<5; i++) {
            System.out.println("process1: " + i);
            Thread.sleep(1000);
        }
    }

//    @Async
    public void process1() throws InterruptedException {
        for (int i = 0; i<5; i++) {
            System.out.println("process2: " + i);
            Thread.sleep(1000);
        }
    }
}
