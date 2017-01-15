package com.thanple.thinking.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 证明：i++不是原子性
 * volatile也不能保证i++为原子性
 * 而AtomicInteger是原子性的
 * */

public class Atomicity_Test2 {
	
	public static int count = 0;
	public static volatile int intVoliate = 0;
	public static AtomicInteger atomicCount = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//连续100次的i++
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){

				@Override
				public  void run() {
					//故意延时（模拟执行一定的代码需要的时间）
					try {
						Thread.sleep((long) (Math.random()*100));
					} catch (InterruptedException e) {}

					System.out.println("count:"+ (++count) );
				}	
			}).start();
		}	
		
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){

				@Override
				public  void run() {
					//故意延时（模拟执行一定的代码需要的时间）
					try {
						Thread.sleep((long) (Math.random()*100));
					} catch (InterruptedException e) {}

					System.out.println("intVoliate:"+ (++intVoliate) );
				}	
			}).start();
		}
		
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					//故意延时（模拟执行一定的代码需要的时间）
					try {
						Thread.sleep((long) (Math.random()*100));
					} catch (InterruptedException e) {}
					
					System.out.println("atomicCount:"+ atomicCount.incrementAndGet());
				}	
			}).start();
		}	
	
	}

}
