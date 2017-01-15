package com.thanple.thinking.designpatterns.behavior;


/*
 * 解释器模式：行为模式之一，解释上下文
 * 
 * Context
    解释器上下文环境类。用来存储解释器的上下文环境，比如需要解释的文法等。
	AbstractExpression
    解释器抽象类。
	ConcreteExpression
    解释器具体实现类。
 * */

//Context：提供数据，相当于Model
class Context1{
	private int num1;
	private int num2;
	
	public Context1(int num1,int num2){
		this.num1 = num1;
		this.num2 = num2;
	}
	public int getNum1() {
		return num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
}

interface Expression{	public int interpret(Context1 context);}

class PlusExpression implements Expression{
	@Override
	public int interpret(Context1 context) {
		return context.getNum1() + context.getNum2(); 
	}
}
class MinusExpression implements Expression{
	@Override
	public int interpret(Context1 context) {
		return context.getNum1() - context.getNum2(); 
	}
}

public class Interpreter {

	public static void main(String[] args) {
		
		int result = new MinusExpression().interpret(new Context1(new PlusExpression().interpret(new Context1(9,2)),8));
		System.out.println("9+2-8="+result);
		
	}

}
