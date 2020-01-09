package com.hearain.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/2 0002 11:00
 * @version: 1.1.0
 * @description:可重入锁
 */
public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;


    @Override
    public void run() {
        for(int j = 0; j < 10000000; j++){
            lock.lock();
            try{
                i++;
            }finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws Exception{
        ReenterLock rt = new ReenterLock();
        Thread t1 = new Thread(rt);
        Thread t2 = new Thread(rt);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
