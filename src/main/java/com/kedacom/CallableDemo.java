package com.kedacom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 在Java 5之后，任务分两类：一类是实现了Runnable接口的类，
 * 一类是实现了Callable接口的类。两者都可以被ExecutorService执行，
 * 但是Runnable任务没有返回值，而Callable任务有返回值。
 * 并且Callable的call()方法只能通过ExecutorService的submit(Callable task) 
 * 方法来执行，并且返回一个 Future，是表示任务等待完成的 Future。
 * @author mengxianxin
 *
 */
public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();   
        List<Future<String>> resultList = new ArrayList<Future<String>>();   

        //创建10个任务并执行   
        for (int i = 0; i < 10; i++){   
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中   
            Future<String> future = executorService.submit(new TaskWithResult(i));   
            //将任务执行结果存储到List中   
            resultList.add(future); 
            System.out.println(i);
        }   

        //遍历任务的结果   
        for (Future<String> fs : resultList){   
                try{   
                    while(!fs.isDone());//Future返回如果没有完成，则一直循环等待，直到Future返回完成  
                    System.out.println(fs.get());     //打印各个线程（任务）执行的结果   
                }catch(InterruptedException e){   
                    e.printStackTrace();   
                }catch(ExecutionException e){   
                    e.printStackTrace();   
                }finally{   
                    //启动一次顺序关闭，执行以前提交的任务，但不接受新任务  
                    executorService.shutdown();   
                }   
        }   
    }   
}   


class TaskWithResult implements Callable<String>{   
    private int id;   

    public TaskWithResult(int id){   
        this.id = id;   
    }   

    /**  
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法， 
     * 则该方法自动在一个线程上执行 
     */   
    public String call() throws Exception {  
    	Thread.sleep(1000);
        System.out.println("call()方法被自动调用！！！    " + Thread.currentThread().getName());   
        //该返回结果将被Future的get方法得到  
        return "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();   
    }   
}  


