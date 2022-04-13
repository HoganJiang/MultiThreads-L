package com.yijiang.juc.p_008_threadlocalWithReference;

/**
 * @Auther: jiangyi
 * @Date: 2022-01-08
 * @Description: com.yijiang.juc.p_008_threadlocalWithReference
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
