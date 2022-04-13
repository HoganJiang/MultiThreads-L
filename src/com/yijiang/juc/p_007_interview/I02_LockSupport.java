package com.yijiang.juc.p_007_interview;

import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-07
 * @Description: com.yijiang.juc.p_007_interview
 * 打印A1B2C3...Z26
 */
public class I02_LockSupport {

    static Thread t1, t2;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            int initWord = 'A';
            do{
                System.out.print((char)initWord);
                initWord++;
                LockSupport.unpark(t2);
                LockSupport.park();
            }while(initWord <= (int)('Z'));
        });

        t2 = new Thread(() -> {
            int iniNumber = 1;
            do{
                LockSupport.park();
                System.out.print(iniNumber);
                iniNumber++;
                LockSupport.unpark(t1);
            } while (iniNumber <= 26);
        });

        t1.start();
        t2.start();
    }

}
