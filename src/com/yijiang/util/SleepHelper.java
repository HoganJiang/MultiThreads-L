package com.yijiang.util;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description:
 */
public class SleepHelper {

    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMilli(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
