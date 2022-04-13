package com.yijiang.juc.p_001_endthread;

import com.yijiang.util.SleepHelper;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_001_endthread
 */
public class T05_Stop {

    public static void stop_end_method() {

        Thread thread = new Thread(() -> {
            while (true) {
                SleepHelper.sleepSeconds(1);
                System.out.println("run run run.");
            }
        });

        thread.start();
        SleepHelper.sleepSeconds(5);
        thread.stop();
    }

    public static void main(String[] args) {
        stop_end_method();
    }

}
