package com.yijiang.juc.p_004_sync;

/**
 * 根据学生问题做的实验，目前还没准备好
 */
public class T31_LockElimination_NotYetReady {

    private void m1() {
        Object o = new Object();
        synchronized (o) {
        }
    }

    private synchronized void m2() {
    }

    public static void main(String[] args) {
        T31_LockElimination_NotYetReady le = new T31_LockElimination_NotYetReady();
        long start = System.currentTimeMillis();
        for (long i = 0; i < 100_0000_0000L; i++) {
            le.m1();
//            le.m2();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
