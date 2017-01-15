package com.thanple.thinking.designpatterns.behavior;

/*
 * 责任链模式
 * 
 * MyHandler本身也是Handler的一个实例（实现了Handler接口），又内置一个Handler
 * 从而实现链的传递
 * 
 * */
interface Handler{
	public void operator();
}

abstract class AbstractHandler{
	private Handler handler;
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
class MyHandler extends AbstractHandler implements Handler{
	private String name;
	public MyHandler(String name){
		this.name = name;
	}
	@Override
	public void operator() {
		System.out.println(name+" deal!");
		if(getHandler()!=null){
			getHandler().operator();
		}
	}
}

public class ChainOfResponsibility {

	public static void main(String[] args) {
		MyHandler h1 = new MyHandler("h1");
		MyHandler h2 = new MyHandler("h2");
		MyHandler h3 = new MyHandler("h3");
		h1.setHandler(h2);
		h2.setHandler(h3);
		h1.operator();
	}

}
