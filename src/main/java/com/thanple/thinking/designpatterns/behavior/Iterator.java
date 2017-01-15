package com.thanple.thinking.designpatterns.behavior;

/*
 * 迭代子模式
 * */
interface Collection{
	public IteratorInter iterator();
	public Object get(int i);
	public int size();
}
interface IteratorInter{
	public Object previous();
	public Object next();
	public boolean hasNext();
	public Object first();
}

class MyIterator implements IteratorInter{
	private Collection collection;
	private int pos = -1;
	
	public MyIterator(Collection collection){
		this.collection = collection;
	}
	@Override
	public Object previous() {
		if(pos>0){
			pos--;
		}
		return collection.get(pos);
	}
	@Override
	public Object next() {
		if(pos < collection.size()-1){
			pos++;
		}
		return collection.get(pos);
	}
	@Override
	public boolean hasNext() {
		return pos < collection.size()-1;
	}

	@Override
	public Object first() {
		return collection.get(pos = 0);
	}
}

class MyCollection implements Collection{
	public String[] str = {"A","B","C","D","E"};
	
	@Override
	public IteratorInter iterator() {
		return new MyIterator(this);
	}
	@Override
	public Object get(int i) {
		return str[i];
	}
	@Override
	public int size() {
		return str.length;
	}
}

public class Iterator {

	public static void main(String[] args) {
		Collection collection = new MyCollection();
		IteratorInter it = collection.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}

	}

}
