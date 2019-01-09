package cn.freeexchange.concurrent.beauty.ch6;

import java.util.concurrent.locks.LockSupport;

public class ParkInterruptedExample {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("child thread begin park!");
				
				//调用park方法,挂起自己,只有被中断才会退出循环
				while(!Thread.currentThread().isInterrupted()) {
					LockSupport.park();
				}
				
				System.out.println("child thread unpark!");
			}
		});
		
		//启动子线程
		thread.start();
		
		Thread.sleep(1000);
		
		System.out.println("main thread begin unpark!");
		
		//
		//LockSupport.unpark(thread);
		//
		thread.interrupt();
	}
}
