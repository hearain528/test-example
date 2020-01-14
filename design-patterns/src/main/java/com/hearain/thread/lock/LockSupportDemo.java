package com.hearain.thread.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/10 0010 11:40
 * @version: 1.1.0
 * @description:
 */
public class LockSupportDemo {

    public static Object u = new Object();

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");

    static ChangeObjectThread t2 = new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
                if(Thread.interrupted()){
                    System.out.println(getName() + "被中断了");
                }
            }
            System.out.println(getName() + "线程执行结束");
        }
    }

    public static void main(String[] args) throws Exception{
        t1.start();
        Thread.sleep(100);
        t2.start();
//        LockSupport.unpark(t1);
        t1.interrupt();
        LockSupport.unpark(t2);
//        t1.join();
//        t2.join();
    }

}
