package com.hearain.thread.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/10 0010 17:47
 * @version: 1.1.0
 * @description:
 */
public class DivTaskDemo {

    public static class DivTask implements Runnable{

        int a,b;

        public DivTask(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a/b;
            System.out.println(re);
        }

    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            pools.execute(new DivTask(100, i));
        }
    }


}
