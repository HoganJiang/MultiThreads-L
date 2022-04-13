package com.yijiang.juc.p_003_ordering;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-04
 * @Description: com.yijiang.juc.p_003_ordering
 */
public class T15_NoVisibility {

    private static boolean ready = false;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new ReaderThread();
        t.start();
        number = 42;
        ready = true;
        t.join();
    }


}
