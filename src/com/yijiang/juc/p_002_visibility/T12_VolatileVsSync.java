package com.yijiang.juc.p_002_visibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-27
 * @Description: com.yijiang.juc.p_002_visibility
 */
public class T12_VolatileVsSync {

    /*volatile*/ int count = 0;

    synchronized void m() {
        for (int i = 0; i < 10000; i++)
            count++;
    }

    public static void main(String[] args) {
        T12_VolatileVsSync t = new T12_VolatileVsSync();

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
