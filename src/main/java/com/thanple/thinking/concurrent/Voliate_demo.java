package com.thanple.thinking.concurrent;


/**
 * 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的值。
 * 当把变量声明为volatile类型后,编译器与运行时都会注意到这个变量是共享的.
 * volatile很容易被误用来进行原子性操作。
 * 
 * 
 * */

public class Voliate_demo extends Thread{
	
	static boolean b1 = true;
	static volatile boolean b2 = true;
	
	
	@Override
	public void run() {
		while(b1){
			System.out.println("b1=true");
		}
		System.out.println("b1=false");
	}

	public static void main(String[] args) throws InterruptedException {
		
		new Voliate_demo().start();
		
		Thread.sleep(1000);
		Voliate_demo.b1 = false;
	}




	

}
