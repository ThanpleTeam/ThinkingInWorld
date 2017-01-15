package com.thanple.thinking.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class TaskWithResult implements Callable<String>{

	private int id;
	
	public TaskWithResult(int id){
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult "+id;
	}
	
}

public class Callable_demo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		List<Future<String>> list = new ArrayList<Future<String>>();
		
		for(int i=0;i<10;i++)
			list.add( exec.submit(new TaskWithResult(i)) );

		for(Future<String> fs : list){
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}finally{
				exec.shutdown();
			}
		}
	}

}
