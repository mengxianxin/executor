package com.kedacom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以看出缓存线程池大小是不定值，可以需要创建不同数量的线程，
 * 在使用缓存型池时，先查看池中有没有以前创建的线程，如果有，
 * 就复用.如果没有，就新建新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务
 * @author mengxianxin
 *
 */
public class NewScheduledThreadPool {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            Runnable syncRunnable = new Runnable() {
                @Override
                public void run() {
                	
                	System.out.println(Thread.currentThread().getName());
                }
            };
            executorService.execute(syncRunnable);
        }
      //顺次地关闭ExecutorService,停止接收新的任务，等待所有已经提交的任务执行完毕之后，关闭ExecutorService
        executorService.shutdown();
	}
   

}
