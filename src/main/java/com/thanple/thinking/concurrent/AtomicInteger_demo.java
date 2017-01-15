package com.thanple.thinking.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * AtomicInteger原子类：保证了int类型的原子操作（int i;i++不是原子操作）
 * 
 * */
public class AtomicInteger_demo implements Runnable{

	private AtomicInteger i = new AtomicInteger(0);
	public int getValue() { return i.get(); }  
	
	private void evenIncrement(){
		i.addAndGet(2);
	}
		
	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	
	public static void main(String[] args) {
		
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		} , 5000);
		
		
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicInteger_demo alt = new AtomicInteger_demo();
		exec.execute(alt);
		
		while(true){
			int val = alt.getValue();
			System.out.println(val);
			if(val%2 != 0){
				System.out.println("&2:"+val);
				System.exit(0);
			}
		}

	}

}
