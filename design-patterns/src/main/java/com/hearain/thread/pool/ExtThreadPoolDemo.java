package com.hearain.thread.pool;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/10 0010 17:28
 * @version: 1.1.0
 * @description:
 */
public class ExtThreadPoolDemo {

    public static class MyTask implements Runnable{

        public String name;

        public MyTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行:Thread ID:" + Thread.currentThread().getId() + ",Task name=" + name);
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{

        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成:" + ((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask("TASK-GEYM-" + i);
            es.execute(myTask);
            Thread.sleep(10);
        }
        es.shutdown();

    }

}
