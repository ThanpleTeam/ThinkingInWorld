package com.thanple.thinking.designpatterns.create;

/**
 * 建造者模式
 * */

class Person {
	private String head;
	private String body;
	private String foot;
	public String getHead() {	return head;	}
	public void setHead(String head) {	this.head = head;	}
	public String getBody() {	return body;}
	public void setBody(String body) {	this.body = body;}
	public String getFoot() {	return foot;}
	public void setFoot(String foot) { this.foot = foot;	}
}

interface PersonBuilder {
	void buildHead();
	void buildBody();
	void buildFoot();
	Person buildPerson();
}


class ManBuilder implements PersonBuilder {
	Person person;
	public ManBuilder() {
		person = new Person();
	}
	public void buildBody() {
		person.setBody("建造男人的身体");
	}
	public void buildFoot() {
		person.setFoot("建造男人的脚");
	}
	public void buildHead() {
		person.setHead("建造男人的头");
	}
	public Person buildPerson() {
		return person;
	}
}

class PersonDirector {
	public Person constructPerson(PersonBuilder pb) {
		pb.buildHead();
		pb.buildBody();
		pb.buildFoot();
		return pb.buildPerson();
	}
}


public class Builder {
	public static void main(String[] args) {
		PersonDirector pd = new PersonDirector();
		Person person = pd.constructPerson(new ManBuilder());
		System.out.println(person.getBody());
		System.out.println(person.getFoot());
		System.out.println(person.getHead());
	}
}
