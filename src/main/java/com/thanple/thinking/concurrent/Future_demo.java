package com.thanple.thinking.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Thanple on 2017/1/4.
 */
public class Future_demo implements Callable<Integer>{

    private static AtomicInteger integer = new AtomicInteger();


    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        List<Future> list = new ArrayList<>();

        for(int i=0;i<100;i++){
            list.add(service.submit(new Future_demo()));
        }
        System.out.println("线程启动完毕...");

        for(Future f : list){
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public Integer call() throws Exception {

        Thread.sleep(100);

        return integer.getAndIncrement();
    }
}
