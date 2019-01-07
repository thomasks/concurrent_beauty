package cn.freeexchange.concurrent.beauty.ch5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class IterationExample {
	
	
	public static void main(String[] args) {
		CopyOnWriteArrayList<String> arrayList = 
				new CopyOnWriteArrayList<>();
		arrayList.add("hello");
		arrayList.add("tom");
		
		Iterator<String> itr = arrayList.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
