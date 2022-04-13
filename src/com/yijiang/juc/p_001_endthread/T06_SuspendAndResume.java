package com.yijiang.juc.p_001_endthread;

import com.yijiang.util.SleepHelper;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_001_endthread
 */
public class T06_SuspendAndResume {

    public static void suspend_and_resume() {

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("run run run.");
                SleepHelper.sleepSeconds(1);
            }
        });

        thread.start();
        SleepHelper.sleepSeconds(5);
        thread.suspend();
        SleepHelper.sleepSeconds(3);
        thread.resume();

    }

    public static void main(String[] args) {
        suspend_and_resume();
    }

}
