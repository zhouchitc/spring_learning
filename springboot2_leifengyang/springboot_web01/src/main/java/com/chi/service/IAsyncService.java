package com.chi.service;

/**
 * @author chi
 * @Description: TODO
 * @date 2022/1/3 20:28
 * @Version 1.0
 */
public interface IAsyncService {
    String process();

    void process1() throws InterruptedException;

    void process2() throws InterruptedException;
}
