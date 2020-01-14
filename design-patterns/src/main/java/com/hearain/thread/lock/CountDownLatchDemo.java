package com.hearain.thread.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/9 0009 17:31
 * @version: 1.1.0
 * @description:
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try{
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check Complete");
            end.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0 ; i < 10; i++){
            executorService.submit(demo);
        }
        //等待检查
        end.await();
        //发射火箭
        System.out.println("Send Fire!!!");
        executorService.shutdown();
    }
}
