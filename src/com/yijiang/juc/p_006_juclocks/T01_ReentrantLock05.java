package com.yijiang.juc.p_006_juclocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-07
 * @Description: com.yijiang.juc.p_006_juclocks
 */
public class T01_ReentrantLock05 extends Thread{

    private static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock05 rl = new T01_ReentrantLock05();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        th1.start();
        th2.start();
    }

}
