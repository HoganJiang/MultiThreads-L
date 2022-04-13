package com.yijiang.juc.p_001_endthread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_001_endthread
 */
public class T08_Interrupt {

    public static void interrupt_end_thread() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            long i = 0L;
            while(!Thread.interrupted()){
                i++;
            }
            System.out.println(i); //615278139 663701784
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }

    public static void interrupt_and_lock() throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("1");
            LockSupport.park();
            System.out.println("2");
        });

        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
//        interrupt_end_thread();
        interrupt_and_lock();
    }
}
