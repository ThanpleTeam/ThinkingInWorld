package com.thanple.thinking.designpatterns.create;

/*
 * 工厂方法模式
 * 
 * */

interface Sender{
	public void Send();
}
class MailSender implements Sender{
	@Override
	public void Send(){
		System.out.println("this is mail sender!");
	}
}
class SmsSender implements Sender{
	@Override
	public void Send(){
		System.out.println("this is Sms sender!");
	}
}

//普通工厂
class SendFactory1{
	public Sender produce(String type){
		if("mail".equals(type)){
			return new MailSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}else{
			System.out.println("请输入正确类型");
			return null;
		}
	}
}
//多个工厂方法模式
class SendFactory2{
	public Sender produceMail(){
		return new MailSender();
	}
	public Sender produceSms(){
		return new SmsSender();
	}
}

//静态工厂方法模式
class StaticSendFactory{
	public static Sender produceMail(){
		return new MailSender();
	}
	public static Sender produceSms(){
		return new SmsSender();
	}
}

public class FactoryMethod {
	public static void main(String[] args) {
		
		//普通工厂
		SendFactory1 factory1 = new SendFactory1();
		Sender sender1 = factory1.produce("sms");
		sender1.Send();
		
		//多个工厂方法模式
		SendFactory2 factory2 = new SendFactory2();
		Sender sender2 = factory2.produceMail();
		sender2.Send();
		
		//静态工厂
		Sender sender3 = StaticSendFactory.produceMail();
		sender3.Send();
	}

}
