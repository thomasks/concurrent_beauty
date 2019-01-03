package cn.freeexchange.concurrent.beauty.ch1.threadlocal;

public class ThreadLocalTest1 {
	
	
	//创建线程变量
	//private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	
	public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
	
	public static void main(String[] args) {
		
		//设置线程变量
		threadLocal.set("hello world");
		
		//启动子线程
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				System.out.println("thread:"+threadLocal.get());
				
			}
		});
		
		thread.start();
		
		System.out.println("main:"+threadLocal.get());
	}

}
