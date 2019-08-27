package com.kedacom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 只会创建一个线程，当上一个执行完之后才会执行第二个
 * @author mengxianxin
 *
 */
public class NewSingleThreadExecutor {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            Runnable syncRunnable = new Runnable() {
                @Override
                public void run() {
//                    Log.e(TAG, Thread.currentThread().getName());
                	System.out.println(Thread.currentThread().getName());
                }
            };
            executorService.execute(syncRunnable);
        }
        //顺次地关闭ExecutorService,停止接收新的任务，等待所有已经提交的任务执行完毕之后，关闭ExecutorService
        executorService.shutdown();
		
	}

}
