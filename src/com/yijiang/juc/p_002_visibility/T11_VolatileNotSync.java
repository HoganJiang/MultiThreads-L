package com.yijiang.juc.p_002_visibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-27
 * @Description: com.yijiang.juc.p_002_visibility
 */
public class T11_VolatileNotSync {

    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) count++;
    }

    public static void main(String[] args) {
        T11_VolatileNotSync t = new T11_VolatileNotSync();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);


    }

}
