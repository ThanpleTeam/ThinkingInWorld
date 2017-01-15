package com.thanple.thinking.designpatterns.behavior;

/*
 * 模板方法模式
 * */
abstract class AbstractCalculatorTem{
	public int [] split(String exp,String opt){
		String array[] = exp.split(opt);
		int [] arrayInt = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}
	
	public final int calculate(String exp,String opt){
		int array[] = split(exp,opt);
		return calculate(array[0],array[1]);
	}

	protected abstract int calculate(int i, int j);
}

class PlusCalculator extends AbstractCalculatorTem{
	@Override
	protected int calculate(int i, int j) {
		return i+j;
	}
}

public class TemplateMethod {
	public static void main(String[] args) {
		String exp = "8+8";
		AbstractCalculatorTem cal = new PlusCalculator();
		System.out.println(cal.calculate(exp, "\\+"));
	}
}
