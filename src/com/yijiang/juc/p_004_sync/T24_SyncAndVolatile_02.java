/**
 * 对比前一个小程序，分析一下这个程序的输出
 *
 * @author mashibing
 */

package com.yijiang.juc.p_004_sync;

public class T24_SyncAndVolatile_02 implements Runnable {

    private int count = 10;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            T24_SyncAndVolatile_02 t = new T24_SyncAndVolatile_02();
            new Thread(t, "THREAD" + i).start();
        }
    }

}
