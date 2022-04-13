package com.yijiang.juc.p_006_juclocks;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-07
 * @Description: com.yijiang.juc.p_006_juclocks
 */
public class T02_CountDownLatch {

    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
//        usingJoin();
        usingCountDownLatch();

//        CountDownLatch latch = new CountDownLatch(3);
//
//        System.out.println(latch.getCount());
//        latch.countDown();
//        System.out.println(latch.getCount());
//        latch.countDown();
//        System.out.println(latch.getCount());
//        latch.countDown();
//        System.out.println(latch.getCount());
//        latch.countDown();
//        System.out.println(latch.getCount());
//        Driver driver = new Driver();
//        driver.main1();

    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[1000];
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;

                for (int j = 0; j < 10000; j++) result += j;
                System.out.println(counter++ + " : " + Thread.currentThread().getName() + " is counting down, the count number is - " + latch.getCount());
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) result += j;
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }

}

class Driver { // ...
    void main1() throws InterruptedException {
        int N = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

//        doSomethingElse();            // don't let run yet
        System.out.println("Driver is driving...");
        startSignal.countDown();      // let all threads proceed
//        doSomethingElse();
        System.out.println("Driver is stoping...");
        doneSignal.await();           // wait for all to finish
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() {
        System.out.println("working is working....");
    }
}