package com.hearain.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/10 0010 16:19
 * @version: 1.1.0
 * @description:
 */
public class ScheduleExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        //每两秒执行一次，但是由于任务执行时间大于频率时间，则下次任务执行距上次任务间隔时间为任务执行时间
//        ses.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    Thread.sleep(8000);
//                    System.out.println(System.currentTimeMillis() / 1000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 2, TimeUnit.SECONDS);


        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(8000);
                    System.out.println(System.currentTimeMillis() / 1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

}
