package com.thanple.thinking.designpatterns.behavior;

/*
 * 状态模式
 * */
class State1{
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void method1(){
		System.out.println("execute the first opt!");
	}
	public void method2(){
		System.out.println("execute the second opt!");
	}
}

class Context{
	private State1 state;
	public Context(State1 state){
		this.state = state;
	}
	public State1 getState() {
		return state;
	}
	public void setState(State1 state) {
		this.state = state;
	}
	
	public void method(){
		if("state1".equals(state.getValue())){
			state.method1();
		}else if("state2".equals(state.getValue())){
			state.method2();
		}
	}
}

public class State {

	public static void main(String[] args) {
		State1 state = new State1();
		Context context = new Context(state);
		
		// 设置第一种状态
		state.setValue("state1");
		context.method();
		
		// 设置第二种状态
		state.setValue("state2");
		context.method();

	}

}
