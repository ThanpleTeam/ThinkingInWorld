package com.thanple.thinking.designpatterns.struct;

/*
 *	适配器模式
 *
 *	类的适配器：当希望将一个类转换成另一个新接口的类时
 *	对象适配器：当希望将一个对象转换成满足另一个新接口的对象时
 *	接口适配器：当不希望实现一个接口中的所有方法时 
 *
 * */
class Source{
	public void method1(){
		System.out.println("This is original method");
	}
}
interface Targetable{
	public void method1();	//与原类中的方法相同
	public void method2();	//新类的方法
}

/*	类的适配器	*/
class Adapter1 extends Source implements Targetable{
	@Override
	public void method2() {
		System.out.println("This is the targetable method");
	}
}

/*	对象的适配器	*/
class Wrapper implements Targetable{
	private Source source;
	
	public Wrapper(Source source){
		super();
		this.source = source;
	}
	@Override
	public void method1() {	
		source.method1();
	}

	@Override
	public void method2() {	
		System.out.println("This is the targetable method");
	}	
}

/*	接口适配器	*/
interface Sourceable{
	public void method1();
	public void method2();
}
abstract class Wrapper2 implements Sourceable{
	public void method1(){}
	public void method2(){}
}

class SourceSub1 extends Wrapper2{
	public void method1(){
		System.out.println("The sourceable interface's first Sub1");
	}
}
class SourceSub2 extends Wrapper2{
	public void method2(){
		System.out.println("The sourceable interface's first Sub2");
	}
}

public class Adapter{
	public static void main(String[] args) {
		/*	类的适配器	*/
		Targetable target = new Adapter1();
		target.method1();
		target.method2();
		
		/*	对象的适配器	*/
		Source source = new Source();	//Source没有实现Targetable接口	
		Targetable target2 = new Wrapper(source);	//source对象转到Wrapper，让Wapper实现Targetable接口
		target2.method1();
		target2.method2();
		
		/*	接口的适配器 */
		Sourceable source1 = new SourceSub1();
		Sourceable source2 = new SourceSub2();
		source1.method1();
		source1.method2();
		source2.method1();
		source2.method2();
	}
}
