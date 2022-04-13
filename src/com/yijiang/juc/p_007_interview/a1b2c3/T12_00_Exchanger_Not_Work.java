package com.yijiang.juc.p_007_interview.a1b2c3;

import java.util.concurrent.Exchanger;

public class T12_00_Exchanger_Not_Work {
    private static Exchanger<Character> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < aI.length; i++) {
                try {
                    System.out.print(exchanger.exchange(aI[i]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < aC.length; i++) {
                try {
                    System.out.print(exchanger.exchange(aC[i]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
