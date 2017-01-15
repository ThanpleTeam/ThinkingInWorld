package com.thanple.thinking.designpatterns.create;

import java.util.Vector;

/*
 * 单例设计模式
 * 
 * */

//普通单例
class Singleton1{
	
	private static Singleton1 instance = null;
	private Singleton1(){
	}
	public static Singleton1 getInstance() {
		if(instance==null){
			instance = new Singleton1();
		}
		return instance;
	}
	
	public Object readResolve(){
		return instance;
	}
}

//多线程下的单例模式
class Singleton2{
	private static Singleton2 instance = new Singleton2();
	private Singleton2(){
	}
	public static Singleton2 getInstance() {
		return instance;
	}
}

//单例模式+影子实例属性更新
class Singleton3{
	private static Singleton3 instance = null;
	private Vector properties = null;
	private Singleton3(){
	}
	
	private static synchronized void syncInit(){
		if(instance==null){
			instance = new Singleton3();
		}
	}
	
	public static Singleton3 getInstance(){
		if(instance==null){
			syncInit();
		}
		return instance;
	}
	
	public Vector getProperties(){
		return properties;
	}
	public void updateProperties(){
		Singleton3 shadow = new Singleton3();
		properties = shadow.getProperties();
	}
}

public class Singleton implements Runnable{
	public static void main(String[] args) {
	}
	@Override
	public void run() {
	}
}
