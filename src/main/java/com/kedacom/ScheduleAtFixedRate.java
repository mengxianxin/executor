package com.kedacom;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRate {
	public static void main(String[] args) {
//		scheduledExecutorService();
//		scheduleAtFixedRate();
		scheduleWithFixedDelay();
		
		
	}
	
	/**
	 * 运行结果和newFixedThreadPool类似，不同的是newScheduledThreadPool是延时一定时间之后才执行
	 */
	public static void scheduledExecutorService() {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 20; i++) {
            Runnable syncRunnable = new Runnable() {
                @Override
                public void run() {
                	System.out.println(Thread.currentThread().getName());
                }
            };
            executorService.schedule(syncRunnable, 5000, TimeUnit.MILLISECONDS);
        }
		
	}
	
	
	/**
	 * 创建并执行一个在给定初始延迟后首次启用的定期操作，
	 * 后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，
	 * 然后在initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推
	 */
	public static void scheduleAtFixedRate() {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable syncRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        executorService.scheduleAtFixedRate(syncRunnable, 5000, 3000, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟
	 * 每个任务执行的时间是3秒，时间间隔是3秒。则下一个任务6秒后开始执行
	 */
	public static void scheduleWithFixedDelay() {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable syncRunnable = new Runnable() {
            @Override
            public void run() {
//                Log.e(TAG, Thread.currentThread().getName());
            	System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.scheduleWithFixedDelay(syncRunnable, 5000, 3000, TimeUnit.MILLISECONDS);
		
	}

}
