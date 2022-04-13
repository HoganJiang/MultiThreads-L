package com.yijiang.juc.p_005_atomic;

import com.yijiang.util.SleepHelper;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class T11_TestAtomicBoolean {

    public void m() {
        AtomicBoolean ab = new AtomicBoolean(false);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                boolean b = ab.compareAndSet(false, true);
                System.out.println(b ? "-----成功" : "失败");
            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println();
    }
}
