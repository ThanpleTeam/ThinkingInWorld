package com.thanple.thinking.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 各大阻塞比较
 * Sleep引起的阻塞是可中断的，而IO和syncrinized引起的阻塞不可中断
 * 
 * */

class SleepBlocked implements Runnable{
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception");
		}finally{
			System.out.println("Exiting SleepBlocked.run()");
		}
	}
}

class IOBlocked implements Runnable{
	private InputStream in;
	public IOBlocked(InputStream in){
		this.in = in;
	}
	
	@Override
	public void run() {
		System.out.println("Waiting for read()");
		try {
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()){
				System.out.println("Interrupted from blocked I/O");
			}else{
				throw new RuntimeException(e);
			}
		}finally{
			System.out.println("Exit I/O Blocked.run()");
		}
	}	
}

class SynchronizedBlocked implements Runnable{
	
	public synchronized void fun(){
		while(true){
			Thread.yield();
		}
	}

	@Override
	public void run() {
		new Thread(){
			public void run(){
				fun();
			}
		}.start();
		
	}
	
}

public class Interrupting_test {

	private static ExecutorService exe = Executors.newCachedThreadPool();
	
	public static void test(Runnable r) throws InterruptedException{
		Future<?> future = exe.submit(r);	//提交可返回持有以该任务的上下文
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting "+r.getClass().getName());
		
		future.cancel(true);
		System.out.println("Interrupt sent to "+r.getClass().getName());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("所有线程退出");
		System.exit(0);
	}

}
