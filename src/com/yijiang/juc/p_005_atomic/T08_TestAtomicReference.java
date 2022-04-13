package com.yijiang.juc.p_005_atomic;

import com.yijiang.util.SleepHelper;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_TestAtomicReference {

    private static class Order {
        long sequence;
        long time;

        @Override
        public String toString() {
            return "Order{" +
                    "sequence=" + sequence +
                    ", time=" + time +
                    '}';
        }
    }


    static AtomicReference<Order> order = new AtomicReference<>(new Order());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Order old = order.get();
                Order o = new Order();
                o.sequence = old.sequence + 1;
                o.time = System.currentTimeMillis();

                order.compareAndSet(old, o);


            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(order);
    }
}
