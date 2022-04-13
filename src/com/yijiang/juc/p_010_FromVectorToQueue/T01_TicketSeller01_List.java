package com.yijiang.juc.p_010_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-08
 * @Description: com.yijiang.juc.p_010_FromVectorToQueue
 */
public class T01_TicketSeller01_List {

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }

}
