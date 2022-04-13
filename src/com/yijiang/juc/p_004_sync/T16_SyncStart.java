package com.yijiang.juc.p_004_sync;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-04
 * @Description: com.yijiang.juc.p_004_sync
 */
public class T16_SyncStart {

    private static long n = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronized (T16_SyncStart.class) {
                        n++;
                    }
                }
                latch.countDown();
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        latch.await();
        System.out.println(n);
    }

}
