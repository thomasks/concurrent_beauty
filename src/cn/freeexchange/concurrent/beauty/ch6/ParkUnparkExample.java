package cn.freeexchange.concurrent.beauty.ch6;

import java.util.concurrent.locks.LockSupport;

public class ParkUnparkExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("child thread begin park.");
				//调用park方法,挂起自己
				LockSupport.park();
				
				System.out.println("child thread unpark.");
			}
		});
		
		//启动子线程
		thread.start();
		
		//主线程休眠1秒
		Thread.sleep(1000);
		
		System.out.println("main thread begin unpark!");
		
		
		//调用unpark方法让thread线程持有许可证,然后park方法返回
		LockSupport.unpark(thread);
		
	}

}
