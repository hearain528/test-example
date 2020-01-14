package com.hearain.thread.lock;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/10 0010 14:52
 * @version: 1.1.0
 * @description:
 */
public class RateLimiterDemo {

    static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            if(!limiter.tryAcquire()){
                continue;
            }
//            limiter.acquire();
            new Thread(new Task()).start();
        }
    }


}
