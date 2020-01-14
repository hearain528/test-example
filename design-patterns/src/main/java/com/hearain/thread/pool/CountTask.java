package com.hearain.thread.pool;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/10 0010 18:35
 * @version: 1.1.0
 * @description:
 */
public class CountTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if(canCompute){
            for (long i = start; i <= end;i++ ){
                sum += i;
            }
        }else{
            long step = (start + end)/100;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if(lastOne > end) lastOne = end;
                CountTask subTask = new CountTask(pos, lastOne);
                pos+=step + 1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask t : subTasks){
                sum += t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 20000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try{
            long res = result.get();
            System.out.println("sum=" + res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
