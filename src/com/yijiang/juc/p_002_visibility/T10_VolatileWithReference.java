package com.yijiang.juc.p_002_visibility;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_002_visibility
 * Volatile修饰引用类型，只保证引用类型本身可见，不保证内部字段可见
 */
public class T10_VolatileWithReference {

    private static class InnerClassA {

        private boolean running = true;

        public void m() {
            System.out.println("m start.");
            while (running) {

            }
            System.out.println("m end.");
        }
    }

    private static volatile InnerClassA a = new InnerClassA();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(a::m, "m");

        t1.start();
        TimeUnit.SECONDS.sleep(1);

        a.running = false;

    }

}
