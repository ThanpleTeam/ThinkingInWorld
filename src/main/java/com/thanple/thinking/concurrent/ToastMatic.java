package com.thanple.thinking.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 生产者与消费者实例
 * 
 * 资源：Toast
 * Toaster生产Toast插入ToastQueue，Butterer从ToastQueue取出插入butteredQueue,
 * Jammer从butteredQueue取出插入finishQueue,Eater从finishQueue取出
 * */
class Toast{
	public enum Status{ DRY,BUTTERED,JAMMD}
	private Status status = Status.DRY;
	private final int id;
	public Toast(int id) {	this.id = id; }
	public void butter(){	status = Status.BUTTERED;	}
	public void jam(){	status = Status.JAMMD;}
	public Status getStatus() {	return status;}
	public int getId() {	return id;}
	@Override
	public String toString() {	return "Toast "+id+":"+status;}
}

class ToastQueue extends LinkedBlockingDeque<Toast>{
	private static final long serialVersionUID = 1L;
}

class Toaster implements Runnable{
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue toastQueue) {
		this.toastQueue = toastQueue;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);			
				toastQueue.put(t);			
			} catch (InterruptedException e) {
				System.out.println("Toast中断");
			}
		}
	}
}

class Butterer implements Runnable{
	private ToastQueue dryQueue , butteredQueue;
	public Butterer(ToastQueue dryQueue,ToastQueue butteredQueue) {
		this.dryQueue = dryQueue;
		this.butteredQueue = butteredQueue;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				Toast take = dryQueue.take();
				take.butter();
				System.out.println(take);
				butteredQueue.put(take);
			} catch (InterruptedException e) {
				System.out.println("Butterer中断");
			}
		}
		System.out.println("Butterer退出");
	}	
}

class Jammer implements Runnable{
	private ToastQueue butteredQueue , finishedQueue;
	public Jammer(ToastQueue butteredQueue , ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				Toast take = butteredQueue.take();
				take.jam();
				System.out.println(take);
				finishedQueue.put(take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Eater implements Runnable{
	private ToastQueue finishedQueue;
	private int count = 0;
	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				Toast take = finishedQueue.take();
				if(take.getId() != count++ || take.getStatus() != Toast.Status.JAMMD){
					System.out.println(">>>>> Error : "+take);
					System.exit(1);
				}else{
					System.out.println("Chmop! "+take);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Eater 退出");
	}
	
}

public class ToastMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
				   butteredQueue = new ToastQueue(),
				   finishedQueue = new ToastQueue();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue,butteredQueue));
		exec.execute(new Jammer(butteredQueue,finishedQueue));
		exec.execute(new Eater(finishedQueue));
		
		TimeUnit.SECONDS.sleep(5);
		
		exec.shutdownNow();

	}

}
