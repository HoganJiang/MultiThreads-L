package com.yijiang.juc.p_006_juclocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-07
 * @Description: com.yijiang.juc.p_006_juclocks
 */
public class T01_ReentrantLock04 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 aquire lock, the status of lock is: " + lock);
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted1!");
            } finally {
                System.out.println("t1 is unlock.");
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//                lock.lock();
                lock.lockInterruptibly();
                System.out.println("t2 aquire lock, the status of lock is: " + lock);
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted2!");
            } finally {
                System.out.println("t2 is unlock.");
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

    }

}
