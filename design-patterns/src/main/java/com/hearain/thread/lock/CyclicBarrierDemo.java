package com.hearain.thread.lock;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2020/1/9 0009 17:45
 * @version: 1.1.0
 * @description:
 */
public class CyclicBarrierDemo {

    public static class Solider implements Runnable{

        private String solider;
        private final CyclicBarrier cyclic;

        Solider(CyclicBarrier cyclic, String soliderName){
            this.cyclic = cyclic;
            this.solider = soliderName;
        }

        @Override
        public void run() {
            try{
                System.out.println("等待所有士兵到齐");
                cyclic.await();
                doWork();
                System.out.println("等待所有士兵完成工作");
                cyclic.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        void doWork(){
            try{
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(solider + "任务完成");
        }
    }

    public static class BarrierRun implements Runnable{

        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N){
            this.flag = flag;
            this.N = N;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司令：[士兵" + N + "个，任务完成!]");
            }else{
                System.out.println("司令：[士兵" + N + "个，集合完毕!]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        final int N = 10;
        Thread[] allSolider = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍!!!");
        for (int i = 0; i < N; i++){
            System.out.println("士兵" + i + "报道！");
            allSolider[i] = new Thread(new Solider(cyclic, "士兵" + i));
            allSolider[i].start();
        }

    }

}
