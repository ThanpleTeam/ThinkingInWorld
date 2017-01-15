package com.thanple.thinking.designpatterns.behavior;

/*
 * 命令模式:将来自客户端的请求传入一个对象，从而使你可用不同的请求对客户进行参数化。
 * 	用于“行为请求者”与“行为实现者”解耦，可实现二者之间的松耦合，以便适应变化。分离变化与不变的因素。
 * 
 * Receiver作为Command里的对象成员实现对象组合，然后将Command作为Invoker的对象成员来执行。
 * 
 * Invoker-->Command::exe()-->Receiver::action()
 * 
 * Command模式可应用于
 * a）整个调用过程比较繁杂，或者存在多处这种调用。这时，使用Command类对该调用加以封装，便于功能的再利用。
 * b）调用前后需要对调用参数进行某些处理。
 * c）调用前后需要进行某些额外处理，比如日志，缓存，记录历史操作等。
 * 
 * Command模式有如下效果：
 * a）将调用操作的对象和知道如何实现该操作的对象解耦。
 * b）Command是头等对象。他们可以像其他对象一样被操作和扩展。
 * c）你可将多个命令装配成一个符合命令。
 * d）增加新的Command很容易，因为这无需改变现有的类
 * */

interface CommandInter{
	public void exe();
}
class Receiver{
	public void action(){
		 //真正执行命令操作的功能代码
		System.out.println("command received!");
	}
}
class MyCommand implements CommandInter{
	private Receiver receiver;
	public MyCommand(Receiver receiver){
		this.receiver = receiver;
	}
	@Override
	public void exe() {
		receiver.action();
	}
}

class Invoker{
	private CommandInter command;
	public Invoker(CommandInter command){
		this.command = command;
	}
	public void action(){
		command.exe();
	}
}

public class Command {
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		CommandInter cmd = new MyCommand(receiver);
		
		Invoker invoker = new Invoker(cmd);
		invoker.action();
	}
}
