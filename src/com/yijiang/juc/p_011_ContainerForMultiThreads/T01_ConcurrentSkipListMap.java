package com.yijiang.juc.p_011_ContainerForMultiThreads;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-09
 * @Description: com.yijiang.juc.p_011_ContainerForMultiThreads
 */
public class T01_ConcurrentSkipListMap {

    public static void main(String[] args) {
//        Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = new ConcurrentSkipListMap<>(); //高并发并且排序
        //Map<String, String> map = new Hashtable<>();
        //Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX
        //TreeMap
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }
}
