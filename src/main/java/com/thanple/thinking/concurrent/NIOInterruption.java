package com.thanple.thinking.concurrent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * NIO read阻塞可以被中断
 * */
class NIOBlocked implements Runnable{
	private final SocketChannel sc;
	
	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}
	
	@Override
	public void run() {
		System.out.println("Waiting for read() in "+this);
	
		try {
			sc.read(ByteBuffer.allocate(1));
		} catch (ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptException:"+e.getStackTrace());
		} catch (AsynchronousCloseException e){
			System.out.println("AsynchronousCloseException:"+ e.getStackTrace());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Exiting NIOBlocked.run() "+this);
	}
}

public class NIOInterruption {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		
		InetSocketAddress isa = new InetSocketAddress(8080);
		
		SocketChannel sc1 = SocketChannel.open(isa),
					  sc2 = SocketChannel.open(isa);
		
		Future<?> f = exec.submit(new NIOBlocked(sc1));
		exec.execute(new NIOBlocked(sc2));
		
		exec.shutdown();
		f.cancel(true);
		
		TimeUnit.SECONDS.sleep(1);
		sc2.close();
	}

}
