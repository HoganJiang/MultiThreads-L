package com.yijiang.juc.p_007_interview.a1b2c3;


import java.util.concurrent.locks.LockSupport;

//Locksupport park 当前线程阻塞（停止）
//unpark(Thread t)

public class T02_00_LockSupport {

    /*Thread t = new Thread();
    {
        t.suspend();
        t.resume();
    }*/


    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws Exception {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {

            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2); //叫醒T2
                LockSupport.park(); //T1阻塞 当前线程阻塞
            }

        }, "t1");

        t2 = new Thread(() -> {

            for (char c : aC) {
                LockSupport.park(); //t2挂起
                System.out.print(c);
                LockSupport.unpark(t1); //叫醒t1
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}


