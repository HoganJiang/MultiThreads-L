package com.yijiang.juc.p_001_endthread;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_001_endthread
 */
public class T07_Volatile {

    private static volatile boolean running = true;

    public static void volatile_end_thread() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            long i = 0L;
            while(running){
                i++;
            }
            System.out.println(i);
        });

        t1.start();//1537711291 1521624884
        TimeUnit.SECONDS.sleep(1);
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        volatile_end_thread();
    }
}
