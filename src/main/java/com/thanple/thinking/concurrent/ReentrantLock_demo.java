package com.thanple.thinking.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 总结：使用ReentrantLock加锁代替synchronized可以使互斥阻塞中断
 * */
class BlockedMutex{
	private Lock lock = new ReentrantLock();
	public BlockedMutex(){
		lock.lock();
	}
	public void function(){
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException e) {
			System.out.println("Lock中断");
		}
	}
}

class Blocked2 implements Runnable{
	private BlockedMutex block = new BlockedMutex();
	@Override
	public void run() {
		System.out.println("阻塞前...");
		block.function();
		System.out.println("阻塞后...");
	}
}

public class ReentrantLock_demo {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked2());
		t.start();
		
		TimeUnit.SECONDS.sleep(1);
		t.interrupt();
	}

}
