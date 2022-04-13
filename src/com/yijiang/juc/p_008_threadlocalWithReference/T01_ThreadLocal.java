package com.yijiang.juc.p_008_threadlocalWithReference;

import com.yijiang.util.SleepHelper;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-08
 * @Description: com.yijiang.juc.p_008_threadlocalWithReference
 */
public class T01_ThreadLocal {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            SleepHelper.sleepSeconds(1);
            tl.set(new Person());
            System.out.println(Thread.currentThread().getName() + " : " + tl.get().name);
        },"t1").start();

        new Thread(() -> {
            SleepHelper.sleepSeconds(2);
            System.out.println(Thread.currentThread().getName() + " : " + tl.get());
        },"t2").start();
    }
}

class Person {
    String name = "zhangsan";
}
