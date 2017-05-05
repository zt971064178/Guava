package cn.itcast.guava.ratelimite.test;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimiter限流
 * Created by zhangtian on 2017/5/5.
 */
public class RateLimiterDemo {
    public static void main(String[] args) {
        testNoRateLimiter() ;
        testWithRateLimiter();
    }

    public static void testNoRateLimiter() {
        long start = System.currentTimeMillis() ;
        for (int i = 0; i < 10; i++){
            System.out.println("call execute: "+i);
        }
        long end = System.currentTimeMillis() ;

        System.out.println(end - start);
    }

    public static void testWithRateLimiter() {
        long start = System.currentTimeMillis() ;
        RateLimiter rateLimiter = RateLimiter.create(10.0) ;// 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++){
            rateLimiter.acquire() ;// 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute: "+i);
        }
        long end = System.currentTimeMillis() ;

        System.out.println(end - start);
    }
}
