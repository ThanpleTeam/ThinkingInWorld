package com.thanple.thinking.designpatterns.behavior;

/*
 * 中介者模式: 中介者对象处于核心地位，因为它定义了整个系统中所有具体同事类之间的关系
 * 
 * 每个User里面内置一个中介者，中介者内置所有User（1:n关系）
 *  
 *  1、 结构上起到中转作用。通过中介者对象对关系的封装，使得具体的同事类不再需要显示的引用其他对象，
 *  	它只需要通过中介者就可以完成与其他同事类之间的通信。
 *  2、 行为上起到协作作用。中介者对同事类之间的关系进行封装，
 *  	同事类在不需要知道其他对象的情况下通过中介者与其他对象完成通信。
 *  	在这个过程中同事类是不需要指明中介者该如何做，中介者可以根据自身的逻辑来进行协调，
 *  	对同事的请求进一步处理，将同事成员之间的关系行为进行分离和封装。
 * 
 *缺点就是因为中介者对象封装了对象之间的关联关系，
 *导致中介者对象变得比较庞大，所承担的责任也比较多。
 *它需要知道每个对象和他们之间的交互细节，如果它出问题，将会导致整个系统都会出问题。
 *所以它比较容易应用也很容易误用。
 * */

interface MediatorInterface{
	public void workAll();
}
abstract class User{
	private MediatorInterface mediator;
	
	public User(MediatorInterface mediator){
		this.mediator = mediator;
	}
	
	public MediatorInterface getMediator() {
		return mediator;
	}
	public void setMediator(MediatorInterface mediator) {
		this.mediator = mediator;
	}
	
	public abstract void work();
}
class User1 extends User{
	public User1(MediatorInterface mediator){
		super(mediator);
	}
	@Override
	public void work() {
		System.out.println("user1 exe!");
	}
}
class User2 extends User{
	public User2(MediatorInterface mediator){
		super(mediator);
	}
	@Override
	public void work() {
		System.out.println("user2 exe!");
	}
}
class MyMediator implements MediatorInterface{

	private User user1;
	private User user2;
	
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}
	
}
public class Mediator {

	public static void main(String[] args) {
		
		//中介者
		MyMediator  mediator  = new MyMediator();
		
		//所有User(内置中介者)
		User1 user1 = new User1(mediator);
		User2 user2 = new User2(mediator);
		
		//中介者包含所有User
		mediator.setUser1(user1);
		mediator.setUser2(user2);
		
		mediator.workAll();

	}

}
