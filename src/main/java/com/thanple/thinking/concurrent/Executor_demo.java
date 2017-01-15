package com.thanple.thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class LiftOff implements Runnable{

	protected int countDown = 10;
	protected static int taskCount = 0;
	private final int id = taskCount++;
	
	public LiftOff(){}
	
	public LiftOff(int countDown){
		this.countDown = countDown;
	}
	
	public String status(){
		return "#"+id+"("+ (countDown > 0 ? countDown : "Liftoff") + "),";
	}
	
	@Override
	public void run() {
		
		while(countDown-- > 0){
			System.out.println(status());
			Thread.yield();
		}
		
	}
	
}

public class Executor_demo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for(int i=1;i<=5;i++){
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
