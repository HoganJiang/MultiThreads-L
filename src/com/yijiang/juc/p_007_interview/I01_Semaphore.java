package com.yijiang.juc.p_007_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-07
 * @Description: 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示结束
 * <p>
 * <p>
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * <p>
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么？
 */
public class I01_Semaphore {

    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {
        I01_Semaphore c = new I01_Semaphore();

        Semaphore semaphore = new Semaphore(1);

        t2 = new Thread(() -> {
            System.out.println("t2 start");
            try {
                semaphore.acquire();
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }, "t2");

        t1 = new Thread(() -> {
            System.out.println("t1 start");
            try {
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        t1.start();
    }
}
