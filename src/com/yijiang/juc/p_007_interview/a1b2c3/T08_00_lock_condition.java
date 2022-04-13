package com.yijiang.juc.p_007_interview.a1b2c3;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_00_lock_condition {

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {


                for (char c : aI) {
                    System.out.print(c);
                    condition.signal();  //notify()
                    condition.await(); // wait()
                }

                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            lock.lock(); //synchronized
            try {


                for (char c : aC) {
                    System.out.print(c);
                    condition.signal(); //o.notify
                    condition.await(); //o.wait
                }

                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }
}


