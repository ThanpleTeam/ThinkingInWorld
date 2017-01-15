package com.thanple.thinking.designpatterns.struct;

import java.util.Hashtable;


/*
 * 享元模式：在一个系统中如果有多个相同的对象，那么只共享一份就可以了，不必每个都去实例化一个对象。
 * 常用工厂模式合用
 * 
 * 可减少内存的开销
 * */

abstract class AbsFlyweight{  
	public abstract void operation();  
} 

class ConcreteFlyweight extends AbsFlyweight{  
	private String string;  
	public ConcreteFlyweight(String str){  
		string = str;  
	}  
	@Override
	public void operation()  {  
		System.out.println("Concrete---Flyweight : " + string);  
	}  
}  

class FlyweightFactory{  
	private Hashtable<Object,AbsFlyweight> flyweights = new Hashtable<>(); 
	
	public AbsFlyweight getFlyWeight(Object obj){  
		AbsFlyweight flyweight = (AbsFlyweight) flyweights.get(obj); 
		if(flyweight == null){ 
			//产生新的ConcreteFlyweight  
			flyweight = new ConcreteFlyweight((String)obj);  
			flyweights.put(obj, flyweight); 
		}  
		return flyweight;  
	}  
	
	public int getFlyweightSize(){  return flyweights.size(); }  
}  

class FlyweightPattern{  
	FlyweightFactory factory = new FlyweightFactory();   
	AbsFlyweight fly1;  
	AbsFlyweight fly2;  
	AbsFlyweight fly3;  
	AbsFlyweight fly4;  
	AbsFlyweight fly5;  
	AbsFlyweight fly6;  
	public FlyweightPattern(){  
		fly1 = factory.getFlyWeight("Google");  
		fly2 = factory.getFlyWeight("Qutr");  
		fly3 = factory.getFlyWeight("Google");  
		fly4 = factory.getFlyWeight("Google");  
		fly5 = factory.getFlyWeight("Google");  
		fly6 = factory.getFlyWeight("Google");  
	}  
	public void showFlyweight(){  
		fly1.operation();  
		fly2.operation();  
		fly3.operation();  
		fly4.operation();  
		fly5.operation();  
		fly6.operation();  

		System.out.println("objSize = " + factory.getFlyweightSize());  	
	}  
	
}  


public class Flyweight {

	public static void main(String[] args) {
		FlyweightPattern fp = new FlyweightPattern();  
		fp.showFlyweight(); 
	}

}
