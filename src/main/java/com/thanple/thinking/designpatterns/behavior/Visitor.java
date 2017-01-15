package com.thanple.thinking.designpatterns.behavior;

/*
 * 访问者模式: 表示一个作用于某对象结构中的各元素的操作，
 * 		它使你可以在不改变各元素类的前提下定义作用于这些元素的新操作。
 * 
 * 访问者模式的目的是要把处理从数据结构中分离出来，如果系统有比较稳定的数据结构，
 * 又有易于变化的算法的话，使用访问者模式是个不错的选择，
 * 因为访问者模式使的算法操作的增加变得容易。相反，如果系统的数据结构不稳定，
 * 易于变化，则此系统就不适合使用访问者模式了。
 * 
 * */

interface VisitorInterface{
	public void visit(SubjectInterface sub);
}

class VisitorA implements VisitorInterface{
	@Override
	public void visit(SubjectInterface sub) {
		System.out.println("visitA visit the subject: "+sub.getSubject());
	}
}

class VisitorB implements VisitorInterface{
	@Override
	public void visit(SubjectInterface sub) {
		System.out.println("visitB visit the subject: "+sub.getSubject());
	}
}

interface SubjectInterface{
	public void accept(VisitorInterface visitor);
	public String getSubject();
}

class MySubjectImp implements SubjectInterface{
	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}
	@Override
	public String getSubject() {
		return "love";
	}
}

public class Visitor {

	public static void main(String[] args) {
		VisitorInterface visitor1 = new VisitorA();
		VisitorInterface visitor2 = new VisitorB();
		
		
		SubjectInterface sub = new MySubjectImp();
		sub.accept(visitor1);
		sub.accept(visitor2);

	}

}
