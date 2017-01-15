package com.thanple.thinking.designpatterns.struct;

/*
 * 装饰模式: 给一个对象增加一些新的功能，而且是动态的
 * 应用场景：需要扩展一个类的功能，动态为一个对象增加功能而且还能动态撤销(继承的功能是静态的，不能动态删改)
 * */

interface Decorator_Sourceable{
	public void method();
}
class Decorator_Source implements Decorator_Sourceable{
	@Override
	public void method() {
		System.out.println("The original method");
	}
}

public class Decorator implements Decorator_Sourceable{

	private Decorator_Sourceable source;
	public Decorator(Decorator_Sourceable source){
		super();
		this.source = source;
	}
	
	@Override
	public void method() {
		System.out.println("before decorator!");
		source.method();
		System.out.println("after decorator!");
	}
	
	public static void main(String[] args) {
		Decorator_Sourceable source = new Decorator_Source();
		Decorator_Sourceable obj = new Decorator(source);
		obj.method();
	}

}
