/**
 * 分析一下这个程序的输出
 *
 * @author mashibing
 */

package com.yijiang.juc.p_004_sync;

public class T23_SyncAndVolatile_01 implements Runnable {

    private /*volatile*/ int count = 100;

    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T23_SyncAndVolatile_01 t = new T23_SyncAndVolatile_01();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}
