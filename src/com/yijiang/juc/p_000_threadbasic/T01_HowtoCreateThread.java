package com.yijiang.juc.p_000_threadbasic;

import java.util.concurrent.*;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-02
 * @Description: com.yijiang.juc.p_000_threadbasic
 */
public class T01_HowtoCreateThread {

    static class MyThread_01 extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread_01 is running!");
        }
    }

    static class MyThread_02 implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread_02 is running!");
        }
    }

    static class MyThread_05 implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "MyThread_05 is running!";
        }
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //继承成Thread类
        new MyThread_01().start();
        //实现Runnable接口
        new Thread(new MyThread_02()).start();
        //使用Lamda表达式
        new Thread(() -> {
            System.out.println("MyThread_03 is running!");
        }).start();
        //使用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> System.out.println("MyThread_04 is running!"));
        //线程池执行接收实现Callable的实例对象
        Future<String> f = executorService.submit(new MyThread_05());
        System.out.println(f.get());
        //使用FutureTask
        FutureTask<String> fut = new FutureTask<>(new MyThread_05());
        new Thread(fut).start();
        System.out.println(fut.get());
        executorService.shutdown();
    }

}
