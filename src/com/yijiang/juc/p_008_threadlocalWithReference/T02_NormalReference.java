package com.yijiang.juc.p_008_threadlocalWithReference;

import java.io.IOException;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-08
 * @Description: com.yijiang.juc.p_008_threadlocalWithReference
 */
public class T02_NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC
        System.out.println(m);
        System.in.read();//阻塞main线程，给垃圾回收线程时间执行
    }

}
