package com.thanple.thinking.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 管道：用管道实现阻塞队列进行输入输出
 * */

class Sender implements Runnable{
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();
	
	public PipedWriter getOut() {
		return out;
	}
	
	@Override
	public void run() {	
		while(true){
			for(char c='A';c<='Z';c++){
				try {
					out.write(c);
					TimeUnit.SECONDS.sleep(rand.nextInt(5));
				} catch (IOException e) {
					System.out.println("IO异常");
				} catch (InterruptedException e) {
					System.out.println("打断异常");
				}
			}
		}
	}
}

class Receiver implements Runnable{
	private PipedReader in;
	
	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getOut());
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("read "+(char)in.read() + "...");
			} catch (IOException e) {
				System.out.println("接收异常");
			}
		}
	}
}

public class PipedIO {

	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		
		TimeUnit.SECONDS.sleep(4);
		exec.shutdown();
	}

}
