package com.thanple.thinking.designpatterns.struct;

/*
 * 桥接模式: 把事物和其具体实现分开，使他们可以各自独立的变化
 * 
 * abstract class Bridge{
 * 	private SourceableBri source;
 * }
 * 
 * class Bridge1 extends Bridge;
 * class Bridge2 extends Bridge;
 * 
 * */

interface SourceableBri{
	public void method();
}
class SourceSub1Bri implements SourceableBri{
	@Override
	public void method() {	
		System.out.println("This is the fist sub!");
	}
}
class SourceSub2Bri implements SourceableBri{
	@Override
	public void method() {	
		System.out.println("This is the second sub!");
	}
}

abstract class BridgeAbs{
	private SourceableBri source;
	
	public void method(){
		source.method();
	}
	public SourceableBri getSource(){
		return source;
	}
	public void setSource(SourceableBri source) {
		this.source = source;
	}
}

public class Bridge extends BridgeAbs{
	
	@Override
	public void method(){
		this.getSource().method();
	}

	public static void main(String[] args) {
		BridgeAbs bridge = new Bridge();
		
		/*	调用第一个对象 	*/
		SourceableBri source1 = new SourceSub1Bri();
		bridge.setSource(source1);
		bridge.method();
		
		/*	调用第二个对象 	*/
		SourceableBri source2 = new SourceSub2Bri();
		bridge.setSource(source2);
		bridge.method();
	}
}


