package com.thanple.thinking.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 死锁条件：
 * 1.互斥
 * 2.至少有一个任务它必须拥有一个资源且正在等待获取一个当前被别的任务持有的资源
 * 3.资源不能被任务抢占
 * 4.必须有循环等待
 * 
 * 破坏一个条件即可避免死锁
 * */

//筷子（资源）
class Chopstick{
	private boolean taken = false;
	
	private int id;
	
	public Chopstick(int id) {
		this.id = id;
	}
	
	public synchronized void take(int nThread) throws InterruptedException{
		System.out.println(nThread+"线程正在请求占用"+id+"资源...");
		while(taken){
			wait();
		}
		System.out.println(nThread+"线程占用"+id+"资源成功");	
		taken = true;
	}
	
	public synchronized void drop(int nThread){
		System.out.println(nThread+"线程释放"+id+"资源...");
		taken = false;
		notifyAll();
	}
}

class Philosopher implements Runnable{
	private Chopstick chopstick1 , chopstick2;
	private int id = 0;
	
	private Random rand = new Random(47);
	
	public Philosopher(Chopstick chopstick1 , Chopstick chopstick2 , int id) {
		this.chopstick1 = chopstick1;
		this.chopstick2 = chopstick2;
		this.id = id;
	}
	
	@Override
	public synchronized void run() {
		try {
			while(!Thread.interrupted()){
				
				Thread.sleep((rand.nextInt(100)));
				
				chopstick1.take(id);		
				chopstick2.take(id);
				
				chopstick1.drop(id);
				chopstick2.drop(id);
			}	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Deadlocking {

	public static void main(String[] args) throws InterruptedException {
		
		Chopstick chopstick1  = new Chopstick(1),
				chopstick2 = new Chopstick(2);
		
		Philosopher thread1 = new Philosopher(chopstick1,chopstick2 , 1),
					thread2 = new Philosopher(chopstick2,chopstick1 , 2);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(thread1);
		executorService.execute(thread2);

		executorService.shutdown();
	}

}
