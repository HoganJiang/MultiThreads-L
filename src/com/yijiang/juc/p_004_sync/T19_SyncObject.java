package com.yijiang.juc.p_004_sync;

import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

import javax.swing.plaf.TableHeaderUI;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-28
 * @Description: com.yijiang.juc.p_004_sync
 */
public class T19_SyncObject {

    private static int count = 10;
    private final Object o = new Object();

    public void m() {
        synchronized (o) { //任何线程要执行下面的代码，必须先锁定o
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] t = new Thread[3];
        for (int i = 0; i < 3; i++) {
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    new T19_SyncObject().m();
                }
            }, "Thread-" + i);
        }

        for (Thread thread : t) {
            thread.start();
        }

        for (Thread thread : t) {
            thread.join();
        }

    }

}
