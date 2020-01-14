package com.hearain.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/9 0009 16:59
 * @version: 1.1.0
 * @description:
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) throws Exception{
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("读线程:" + value + "===time:" + System.currentTimeMillis());
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws Exception{
        try{
            lock.lock();
            Thread.sleep(1000);
            value = index;
            System.out.println("写线程:" + value + "===time:" + System.currentTimeMillis());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try{
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunable = new Runnable() {
            @Override
            public void run() {
                try{
                    demo.handleWrite(writeLock, new Random().nextInt());
//                    demo.handleWrite(lock, new Random().nextInt());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++){
            new Thread(readRunnable).start();
        }

        for (int i = 18; i < 20; i++){
            new Thread(writeRunable).start();
        }
    }

}
