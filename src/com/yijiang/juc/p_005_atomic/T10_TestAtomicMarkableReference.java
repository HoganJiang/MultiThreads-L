package com.yijiang.juc.p_005_atomic;

import com.yijiang.util.SleepHelper;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class T10_TestAtomicMarkableReference {

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


    static AtomicMarkableReference<Order> orderRef = new AtomicMarkableReference<>(new Order(), false);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Order old = orderRef.getReference();

                Order o = new Order();
                o.sequence = old.sequence + 1;
                o.time = System.currentTimeMillis();

                orderRef.compareAndSet(old, o, false, true);


            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(orderRef.getReference());
    }
}
