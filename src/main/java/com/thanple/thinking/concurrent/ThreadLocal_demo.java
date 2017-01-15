package com.thanple.thinking.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 总结：ThreadLocal为每个线程存储一个副本，
 * 虽然ThreadLocalVariableHolder里的value为静态，
 * 但是在多线程的情况下，每个线程都会有一个value变量，在每次increment的时候，
 * 每个线程只执行自己的一个副本value的increment，并且互不影响
 * */

class ThreadLocalVariableHolder{
	
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
		private Random rand = new Random(47);
		protected synchronized Integer initialValue(){
			return rand.nextInt();
		}
	};
	
	public static void increment(){
		value.set(value.get()+1);
	}
	
	public static int get(){ return value.get(); }
}

class Accessor implements Runnable{
	private final int id;
	
	public Accessor(int idn){ id = idn; }

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	@Override
	public String toString(){
		return "#"+id+": "+ThreadLocalVariableHolder.get();
	}
}

public class ThreadLocal_demo {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new Accessor(i));
		}
	
		TimeUnit.SECONDS.sleep(3);
		exec.shutdown();

	}

}
