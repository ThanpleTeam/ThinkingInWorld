package com.thanple.thinking.designpatterns.behavior;



/*
 * 备忘录模式
 * */

class Memoto1{
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Memoto1(String value){
		setValue(value);
	}
}

class Original{
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Original(String value){
		setValue(value);
	}
	public Memoto1 createMemento(){
		return new Memoto1(value);
	}
	public void restoreMemento(Memoto1 memoto){
		this.value = memoto.getValue();
	}
}
class Storage{
	private Memoto1 memento;
	public Storage(Memoto1 memento){
		this.memento = memento;
	}
	public Memoto1 getMemento() {
		return memento;
	}
	public void setMemento(Memoto1 memento) {
		this.memento = memento;
	}
}

public class Memento {

	public static void main(String[] args) {
		//创建原始类
		Original origin = new Original("egg");
		
		//创建备忘录
		Storage storage = new Storage(origin.createMemento());
		
		//修改原始类的状态
		System.out.println("初始化状态:"+origin.getValue());
		origin.setValue("niu");
		System.out.println("修改后的状态:"+origin.getValue());
		
		//恢复到原始类的状态
		origin.restoreMemento(storage.getMemento());
		System.out.println("恢复后的状态:"+origin.getValue());

	}

}
