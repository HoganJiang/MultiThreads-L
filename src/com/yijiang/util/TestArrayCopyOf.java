package com.yijiang.util;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-09
 * @Description: com.yijiang.util
 */
public class TestArrayCopyOf {

    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1,3,6,7};
        int[] i1 = Arrays.copyOf(arr, 4);
        int[] i2 = Arrays.copyOf(arr, 3);
        int[] i3 = Arrays.copyOf(arr, 5);
        System.out.println((arr == i1));
        System.out.println((arr == i2));
        System.out.println((arr == i3));

        new Thread(() -> {
            System.out.print("arr: ");
            for(int ele : arr){
                System.out.print(ele + " ");
            }
            System.out.println();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.print("arr: ");
            for(int ele : i1){
                System.out.print(ele + " ");
            }
            System.out.println();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.print("arr: ");
            for(int ele : i2){
                System.out.print(ele + " ");
            }
            System.out.println();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.print("arr: ");
            for(int ele : i3){
                System.out.print(ele + " ");
            }
            System.out.println();
        }).start();
    }
}
