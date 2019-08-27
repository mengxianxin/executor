package com.kedacom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 总共只会创建5个线程， 开始执行五个线程，当五个线程都处于活动状态，
 * 再次提交的任务都会加入队列等到其他线程运行结束，当线程处于空闲状态时会被下一个任务复用
 * @author mengxianxin
 *
 */
public class NewFixedThreadPool {
    private static int a;
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            Runnable syncRunnable = new Runnable() {
                @Override
                public void run() {
                	
                	System.out.println(a++);
                	System.out.println(Thread.currentThread().getName());
                }
            };
            executorService.execute(syncRunnable);
        }
       //顺次地关闭ExecutorService,停止接收新的任务，等待所有已经提交的任务执行完毕之后，关闭ExecutorService
        executorService.shutdown();
		
	}

}
