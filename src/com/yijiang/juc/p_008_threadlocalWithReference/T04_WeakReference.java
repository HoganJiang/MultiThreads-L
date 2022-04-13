package com.yijiang.juc.p_008_threadlocalWithReference;

import com.yijiang.util.SleepHelper;

import java.lang.ref.WeakReference;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-08
 * @Description: com.yijiang.juc.p_008_threadlocalWithReference
 */
public class T04_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> wr = new WeakReference<>(new M());
        System.out.println(wr.get());
        System.gc();
        SleepHelper.sleepSeconds(1);
        System.out.println(wr.get());

        /*ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();*/

    }

}
