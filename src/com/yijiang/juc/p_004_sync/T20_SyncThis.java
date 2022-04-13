package com.yijiang.juc.p_004_sync;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-28
 * @Description: com.yijiang.juc.p_004_sync
 */
public class T20_SyncThis {

    private int count = 10;

    public void m() {
        synchronized (this) { //任何线程要执行下面的代码，必须先锁定o
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
                    new T20_SyncThis().m();
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
