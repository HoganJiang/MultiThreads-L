package com.yijiang.juc.p_010_FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-08
 * @Description: com.yijiang.juc.p_010_FromVectorToQueue
 */
public class T04_TicketSeller04_ConcurrentLinkedQueue {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号：" + i);
    }

    //此段程序的问题：尽管Vector类的size,remove方法都加锁保持同步，但while代码块中的逻辑依旧是没有加锁
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
//                    synchronized (tickets) {
                        String poll = tickets.poll();
                        if(poll == null) break;
                        else System.out.println("销售了--" + poll);
//                        System.out.println("销售了--" + tickets.remove(0));
//                    }
                }
            }).start();
        }
    }
}
