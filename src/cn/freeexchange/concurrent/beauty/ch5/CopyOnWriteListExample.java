package cn.freeexchange.concurrent.beauty.ch5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListExample {
	
	private static volatile CopyOnWriteArrayList<String> arrayList = 
			new CopyOnWriteArrayList<>();
	
	
	public static void main(String[] args) throws InterruptedException {
		
		arrayList.add("hello");
		arrayList.add("tom");
		arrayList.add("welcome");
		arrayList.add("to");
		arrayList.add("china");
		
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				arrayList.set(1, "land");
				
				//删除元素
				arrayList.remove(2);
				arrayList.remove(3);
			}
		});
		
		
		//保证在修改线程启动前获取迭代器
		Iterator<String> itr = arrayList.iterator();
		
		//启动线程
		threadOne.start();
		
		//等待子线程执行完毕
		threadOne.join();
		
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}		
	}
	
}
