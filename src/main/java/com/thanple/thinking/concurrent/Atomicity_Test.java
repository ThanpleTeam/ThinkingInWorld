package com.thanple.thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 原子操作总结
 * */

class Atomicity implements Runnable{
	private int i = 0;
	private synchronized void evenIncrement(){	i+=2; }

	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	public  int getValue() { return i; }
}



public class Atomicity_Test {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Atomicity at1 = new Atomicity();
		exec.execute(at1);
		
		
		//非原子
		while(true){
			int value = at1.getValue();
			System.out.println(value);
			if(value % 2!=0){
				System.out.println(value);
				System.exit(0);
			}
		}
		
		

	}

}
