package com.yijiang.juc.p_008_threadlocalWithReference;

import com.yijiang.util.SleepHelper;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-29
 * @Description: com.yijiang.juc.p_008_threadlocalWithReference
 */
public class T05_PhantomReference {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        System.out.println(phantomReference.get());

        ByteBuffer b = ByteBuffer.allocateDirect(1024);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                SleepHelper.sleepSeconds(1);
                System.out.println(phantomReference.get());
            }
        }).start();

        //垃圾回收线程
        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
                }
            }
        }).start();

        SleepHelper.sleepSeconds(1);

    }

}
