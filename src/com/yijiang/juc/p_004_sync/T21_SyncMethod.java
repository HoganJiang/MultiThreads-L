package com.yijiang.juc.p_004_sync;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-28
 * @Description: com.yijiang.juc.p_004_sync
 */
public class T21_SyncMethod {

    private int count = 10;

    public synchronized void m() {//synchronized(this)
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) throws InterruptedException {

        T21_SyncMethod t21_syncMethod = new T21_SyncMethod();

        Thread[] t = new Thread[3];
        for (int i = 0; i < 3; i++) {
            t[i] = new Thread(t21_syncMethod::m, "Thread-" + i);
        }

        for (Thread thread : t) {
            thread.start();
        }

        for (Thread thread : t) {
            thread.join();
        }

    }

}
