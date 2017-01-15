package com.thanple.thinking.designpatterns.create;

/*
 * 抽象工厂模式
 * @消除工厂模式中类的创建依赖工厂类
 * @扩展性较好
 * */

interface Provider{
	public Sender produce();
}
class SendSmsFactory implements Provider{
	@Override
	public Sender produce() {
		return new SmsSender();
	}
}
class SendMailFactory implements Provider{
	@Override
	public Sender produce() {
		return new MailSender();
	}
}

public class AbstractFactory {
	public static void main(String[] args) {
		Provider provider = new SendMailFactory();
		Sender sender = provider.produce();
		sender.Send();
	}
}
