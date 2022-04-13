package com.yijiang.juc.p_007_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-07
 * @Description: 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示结束
 */
public class I01_Volatile {

    static class Container<T> {

        private volatile Integer size;
        private List<T> container;

        public Container() {
            this.container = new ArrayList<>();
            this.size = 0;
        }

        public void add(T ele) {
            container.add(ele);
            size++;
        }

        public Integer size() {
            return size;
        }

    }

    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        Container<Integer> container = new Container<>();
        Thread t2 = new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    System.out.println("t2 end!");
                    break;
                }
            }
        });

        t2.start();

        Thread t1 = new Thread(() -> {
            try {
                writeLock.lock();
                Integer item = 0;
                int i = 0;
                while (i < 10) {
                    container.add(item++);
                    System.out.println("current size : " + container.size());
                    i++;
                }
            } finally {
                writeLock.unlock();
            }
        });

        t1.start();

    }


}
