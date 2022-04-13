package com.yijiang.juc.p_004_sync;

import com.yijiang.util.SleepHelper;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-04
 * @Description: com.yijiang.juc.p_004_sync
 */
public class T17_WhatIsLock {

    private static Object o = new Object();

    public static void main(String[] args) {
        Runnable r = () -> {
            //不加synchronized时，不会序列化的执行
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " start!");
                SleepHelper.sleepSeconds(2);
                System.out.println(Thread.currentThread().getName() + " end!");
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(r).start();
        }
    }
}
