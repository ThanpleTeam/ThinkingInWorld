package com.thanple.thinking.designpatterns.struct;

import java.util.ArrayList;
import java.util.List;


/*
 * 组合模式：解耦了客户程序与复杂元素内部结构，从而使客户程序可以向处理简单元素一样来处理复杂元素。
 * 
 * 1．你想表示对象的部分-整体层次结构
 * 2．你希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
 * 
  *  Thinking：枝节点A里面的集合为 Collection<? super A> ，该集合可以组合其他叶节点
 * */

//抽象接口类
abstract class Company {  
    private String name;  
    public Company(String name) {  setName(name); }  
    public Company() {}  
  
    public String getName() {   return name;  }  
  
    public void setName(String name) {  this.name = name;  }  
  
    protected abstract void add(Company company);  
    protected abstract void romove(Company company);  
    protected abstract void display(int depth);  
}  

//枝结点类
class ConcreteCompany extends Company {  
    private List<Company> cList;  
    public ConcreteCompany() {  
        cList = new ArrayList<Company>();  
    }  
    public ConcreteCompany(String name) {  
        super(name);   
        cList = new ArrayList<Company>() ;   
    }  
  
    @Override  
    protected void add(Company company) {  
        cList.add(company);  
    }  
  
    @Override  
    protected void display(int depth) {  
        StringBuilder sb = new StringBuilder("");  
        for (int i = 0; i < depth; i++) {  
            sb.append("-");   
        }  
        System.out.println(new String(sb) + this.getName());  
        for (Company c : cList) {  
            c.display(depth + 2);  
        }  
    }  
  
    @Override  
    protected void romove(Company company) {  
        cList.remove(company);  
    }  
}  

//两个叶结点类：
class FinanceDepartment extends Company {  
    public FinanceDepartment(){}    
    public FinanceDepartment(String name){  
        super(name);  
    }  
      
    @Override  
    protected void add(Company company) { }  
  
    @Override  
    protected void display(int depth) {  
        StringBuilder sb = new StringBuilder("");  
        for (int i = 0; i < depth; i++) {  
            sb.append("-");  
        }  
        System.out.println(new String(sb) + this.getName() ) ;   
    }  
  
    @Override  
    protected void romove(Company company) { }  
      
}  

class HRDepartment extends Company {  
    public HRDepartment(){}     
    public HRDepartment(String name){  
        super(name);  
    }  
      
    @Override  
    protected void add(Company company) {}  
  
    @Override  
    protected void display(int depth) {  
        StringBuilder sb = new StringBuilder("");  
        for (int i = 0; i < depth; i++) {  
            sb.append("-");   
        }  
        System.out.println(new String(sb) + this.getName() ) ;   
    }  
  
    @Override  
    protected void romove(Company company) {}  
      
}  

//客户端
public class Composite{
	public static void main(String[] args) {
		
		//root
		Company root = new ConcreteCompany();  
        root.setName("北京总公司");  
        root.add(new HRDepartment("总公司人力资源部"));  
        root.add(new FinanceDepartment("总公司财务部"));  
        
        Company shandongCom = new ConcreteCompany("山东分公司");  
        shandongCom.add(new HRDepartment("山东分公司人力资源部"));  
        shandongCom.add(new FinanceDepartment("山东分公司账务部")); 
        
        Company zaozhuangCom = new ConcreteCompany("枣庄办事处");  
        zaozhuangCom.add(new FinanceDepartment("枣庄办事处财务部"));  
        zaozhuangCom.add(new HRDepartment("枣庄办事处人力资源部"));  
        
        Company jinanCom = new ConcreteCompany("济南办事处");  
        jinanCom.add(new FinanceDepartment("济南办事处财务部"));  
        jinanCom.add(new HRDepartment("济南办事处人力资源部"));   
        
        shandongCom.add(jinanCom);  
        shandongCom.add(zaozhuangCom);  
        
        Company huadongCom = new ConcreteCompany("上海华东分公司");  
        huadongCom.add(new HRDepartment("上海华东分公司人力资源部"));  
        huadongCom.add(new FinanceDepartment("上海华东分公司账务部"));  
        
        Company hangzhouCom = new ConcreteCompany("杭州办事处");  
        hangzhouCom.add(new FinanceDepartment("杭州办事处财务部"));  
        hangzhouCom.add(new HRDepartment("杭州办事处人力资源部"));  
        
        Company nanjingCom = new ConcreteCompany("南京办事处");  
        nanjingCom.add(new FinanceDepartment("南京办事处财务部"));  
        nanjingCom.add(new HRDepartment("南京办事处人力资源部"));  
        
        huadongCom.add(hangzhouCom);  
        huadongCom.add(nanjingCom);   
        
        root.add(shandongCom);  
        root.add(huadongCom);  
        root.display(0);  
	}
}
