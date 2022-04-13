package com.yijiang.juc.p_003_ordering;

import java.io.IOException;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-04
 * @Description: com.yijiang.juc.p_003_ordering
 */
public class T14_ThisEscape {

    private int m = 8;

    public T14_ThisEscape(){
        new Thread(() -> {
            System.out.println(m);
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new T14_ThisEscape();
//        System.in.read();
    }
}
