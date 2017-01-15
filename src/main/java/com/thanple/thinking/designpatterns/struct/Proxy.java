package com.thanple.thinking.designpatterns.struct;

/*
 * 代理模式
 * */

interface SourceablePro{
	public void method();
}

class SourcePro implements SourceablePro{
	@Override
	public void method() {
		System.out.println("The original method");
	}
}

public class Proxy implements SourceablePro{
	private SourcePro source;
	
	public Proxy(){
		this.source = new SourcePro();
	}

	@Override
	public void method() {
		before();
		source.method();
		after();
	}
	private void before(){
		System.out.println("before proxy...");
	}
	private void after(){
		System.out.println("after proxy...");
	}

	public static void main(String[] args) {
		SourceablePro source = new Proxy();
		source.method();
	}

}
