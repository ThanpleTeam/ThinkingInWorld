package com.thanple.thinking.designpatterns.create;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 原型模式
 * */
class SerializableObject implements Serializable{
	private static final long serialVersionUID = 1955727995740015704L;
}

public class Prototype implements Cloneable,Serializable{
	private static final long serialVersionUID = -7056911806369542474L;
	
	private String str;
	private SerializableObject obj;
	
	/*	浅拷贝 */
	public Object clone() throws CloneNotSupportedException{
		Prototype proto = (Prototype)super.clone();
		return proto;
	}
	
	/* 深拷贝 */
	public Object deepClone() throws IOException, ClassNotFoundException{
		//写入当前对象的二进制流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		//读出二进制产生的新对象
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		Prototype pro = new Prototype();
		pro.setStr("test");
		
		//浅拷贝
		Prototype newPro1 = (Prototype)pro.clone();
		System.out.println(newPro1.getStr()==pro.getStr());
		
		//深拷贝
		Prototype newPro2 = (Prototype)pro.deepClone();
		System.out.println(newPro2.getStr()==pro.getStr());
		System.out.println(newPro2.getStr().equals(pro.getStr()));
	}

	//get and set
	public SerializableObject getObj() {
		return obj;
	}
	public void setObj(SerializableObject obj) {
		this.obj = obj;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
}
