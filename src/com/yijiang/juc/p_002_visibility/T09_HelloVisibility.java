package com.yijiang.juc.p_002_visibility;

import com.yijiang.util.SleepHelper;

import java.io.IOException;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_002_visibility
 */
public class T09_HelloVisibility {

    private static /*volatile*/ boolean running = true;

    public static void m() {
        System.out.println("m: start.");
        while (running) {

        }
        System.out.println("m: end.");
    }

    public static void main(String[] args) throws IOException {

        new Thread(T09_HelloVisibility::m, "m").start();

        SleepHelper.sleepSeconds(1);

        running = false;

    }


}
