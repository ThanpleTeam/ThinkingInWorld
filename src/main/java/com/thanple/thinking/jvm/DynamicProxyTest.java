package com.thanple.thinking.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


interface Subject{
    public void rent();
    public void hello(String str);
}

class RealSubject implements Subject{
    @Override
    public void rent(){
        System.out.println("租房...");
    }
    
    @Override
    public void hello(String str){
        System.out.println("你好: " + str);
    }
}

public class DynamicProxyTest implements InvocationHandler
{
    //　这个就是我们要代理的真实对象
    private Object subject;
    
    //构造方法，给我们要代理的真实对象赋初值
    public DynamicProxyTest(Object subject){
        this.subject = subject;
    }
    
    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("***************代理之前***************");  
        System.out.println("代理方法:" + method);
        
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        
        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("***************代理之后***************");
        
        return null;
    }
    
    
    
    
    public static void main(String[] args)
    {
        //我们要代理的真实对象
        Subject realSubject = new RealSubject();

        //我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxyTest(realSubject);

        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        
        Subject subject = (Subject)java.lang.reflect.Proxy.newProxyInstance(
        		DynamicProxyTest.class.getClassLoader(),
        		RealSubject.class.getInterfaces(), 
        		handler);
        
        System.out.println(subject.getClass());
        subject.rent();
        subject.hello("Thanple");
        
        
    }
}


