package com.yijiang.juc.p_000_threadbasic;

import com.yijiang.util.SleepHelper;

import java.util.PrimitiveIterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-03
 * @Description: com.yijiang.juc.p_000_threadbasic
 */
public class T04_Interrupt {

    //to test interrupt and isinterrupted
    public static void interrupt_and_is_interrupted() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("t1 is interrupted.");
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }

    //to test interrupted
    public static void interrupted() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            for (; ; ) {
                System.out.println("Thread t2: " + Thread.currentThread().isInterrupted());
                if (Thread.interrupted()) {
                    System.out.println("Thread is interrupted!");
                    System.out.println(Thread.interrupted());
                    break;
                }
            }
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }

    //to test interrupt and sleep
    public static void interrupt_and_sleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //当前线程被设置打断的标志位后，捕获异常以后，JDK会自动的将标志位复位至false
                System.out.println("thread is interrupt with the interrupt status: " + Thread.currentThread().isInterrupted());
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }

    //to test interrupt and wait
    public static void interrupt_and_wait() throws InterruptedException {

        final Object o = new Object();

        Thread thread = new Thread(() -> {
            try {
                synchronized (o) {
                    o.wait();
                }
            } catch (InterruptedException e) {
                //当前线程被设置打断的标志位后，捕获异常以后，JDK会自动的将标志位复位至false
                System.out.println("thread is interrupt with the interrupt status: " + Thread.currentThread().isInterrupted());
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }

    //interrupt能否打断正在争抢锁（synchronized）的线程？答案是不能
    public static void interrupt_and_synchronized() throws InterruptedException {
        final Object o = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (o) {
                SleepHelper.sleepSeconds(10);
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (o) {

            }
            System.out.println("t2 end!");
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);

        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }

    //interrupt能否打断正在争抢锁（lock）的线程？答案是不能
    public static void interrupt_and_lock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");

        });

        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }

    //interrupt能否打断正在争抢锁（lock）的线程，若要允许打断正在竞争的锁，应该怎么办？答案是：使用lock.lockInterruptibly
    public static void interrupt_and_lockInterruptibly() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread-t1 occurs exception!");
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("Thread-t2 occurs exception!");
            } finally {
//                lock.unlock();
            }
            System.out.println("t2 end!");
        });

        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
//        interrupt_and_is_interrupted();
//        interrupted();
//        interrupt_and_sleep();
//        interrupt_and_wait();
//        interrupt_and_synchronized();
//        interrupt_and_lock();
        interrupt_and_lockInterruptibly();
    }
}
