package com.thanple.thinking.designpatterns.behavior;

import java.util.Enumeration;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/*
 * 观察者模式
 * 
 * 
 * Subject实体中保存着对Observer的集合引用
 * */

interface ObserverInter{
	public void update();
}
class Observer1 implements ObserverInter{
	@Override
	public void update() {
		System.out.println("observer1 has received!");
	}
}
class Observer2 implements ObserverInter{
	@Override
	public void update() {
		System.out.println("observer2 has received!");
	}
}

interface Subject{
	public void add(ObserverInter observer);
	public void delete(ObserverInter observer);
	public void notifyObserver();
	public void operation();
}
abstract class AbstractSubject implements Subject{
	private Vector<ObserverInter> vector = new Vector<ObserverInter>();

	@Override
	public void add(ObserverInter observer) {
		vector.add(observer);
	}

	@Override
	public void delete(ObserverInter observer) {
		vector.remove(observer);
	}

	@Override
	public void notifyObserver() {
		Enumeration<ObserverInter> enumo = vector.elements();
		while(enumo.hasMoreElements()){
			enumo.nextElement().update();
		}
	}
}
class MySubject extends AbstractSubject{
	@Override
	public void operation() {
		System.out.println("update self!");
		notifyObserver();
	}
}

public class Observer {

	public static void main(String[] args) {
		Subject sub = new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		sub.operation();
	}

}
