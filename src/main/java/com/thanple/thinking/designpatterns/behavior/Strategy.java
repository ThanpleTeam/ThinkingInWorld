package com.thanple.thinking.designpatterns.behavior;

/*
 * 策略模式
 * */

interface ICalculator{
	public int calculate(String exp);
}
abstract class AbstractCalculator implements ICalculator{
	public int [] split(String exp,String opt){
		String array[] = exp.split(opt);
		int [] arrayInt = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}
}
class Plus extends AbstractCalculator {
	@Override
	public int calculate(String exp) {
		int [] arrayInt = super.split(exp, "\\+");
		return arrayInt[0]+arrayInt[1];
	}
}
class Minus extends AbstractCalculator{
	@Override
	public int calculate(String exp) {
		int [] arrayInt = super.split(exp, "-");
		return arrayInt[0]-arrayInt[1];
	}
}
class Multiply extends AbstractCalculator {
	@Override
	public int calculate(String exp) {
		int [] arrayInt = super.split(exp, "\\*");
		return arrayInt[0]*arrayInt[1];
	}
}

public class Strategy {
	public static void main(String[] args) {
		String exp = "2+8";
		ICalculator cal = new Plus();
		System.out.println(cal.calculate(exp));
	}

}
