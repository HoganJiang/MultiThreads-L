package com.yijiang.jol;

import com.yijiang.util.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-04
 * @Description: com.yijiang.jol
 */
public class T01_JolBasic {

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();

        String s1 = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s1);

        //sleep 5s 是的JVM启动完成
        TimeUnit.SECONDS.sleep(5);

        TestClass testClass = new TestClass();
        testClass.start();

        TimeUnit.SECONDS.sleep(1);

    }

}

class TestClass extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(ClassLayout.parseInstance(this).toPrintable());
        }
    }

}