package com.yijiang.juc.p_000_threadbasic;

import com.yijiang.util.SleepHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description:
 */
public class T03_ThreadState {

    //NEW_RUNNABLE_TERMINATED
    public static void new_runnable_terminated() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("2: " + Thread.currentThread().getState());
            for (int i = 0; i < 3; i++) {
                SleepHelper.sleepSeconds(1);
                System.out.print(i + " ");
            }
            System.out.println();
        });
        System.out.println("1: " + thread.getState());
        thread.start();
        thread.join();
        System.out.println("3: " + thread.getState());
    }

    //WAITING_TIMED WAITING
    public static void waiting_timedWaiting() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                LockSupport.park();
                System.out.println("current thread is running.");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("4: " + thread.getState());

        LockSupport.unpark(thread);
        System.out.println("5: " + thread.getState());
    }

    //BLOCKED
    public static void blocked() {
        final Object o = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t1 get o!");
            }
        });

        new Thread(() -> {
            synchronized (o) {
                SleepHelper.sleepSeconds(5);
            }
        }).start();

        SleepHelper.sleepSeconds(1);

        t1.start();
        SleepHelper.sleepSeconds(1);
        System.out.println(t1.getState());
    }

    //difference between synchronized and lock
    public static void different_synchronized_and_lock() throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println("thread1 get lock!");
            lock.unlock();
        });

        new Thread(() -> {
            lock.lock();
            SleepHelper.sleepSeconds(5);
            lock.unlock();
        }).start();

        TimeUnit.SECONDS.sleep(1);
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread1.getState());
    }

    //the status of thread under park()
    public static void thread_under_park() throws InterruptedException {
        Thread thread = new Thread(() -> {
            LockSupport.park();
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.getState());
        LockSupport.unpark(thread);
    }

    public static void main(String[] args) throws InterruptedException {
//        new_runnable_terminated();
//        waiting_timedWaiting();
//        blocked();
//        different_synchronized_and_lock();
        thread_under_park();
    }

}
