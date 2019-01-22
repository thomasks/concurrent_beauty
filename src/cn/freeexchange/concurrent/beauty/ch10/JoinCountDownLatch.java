package cn.freeexchange.concurrent.beauty.ch10;

import java.util.concurrent.CountDownLatch;

public class JoinCountDownLatch {
	
	//创建一个CountDownLatch实例
	private static volatile CountDownLatch countdownLatch = 
			new CountDownLatch(2);
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countdownLatch.countDown();
				}
				System.out.println("child threadOne over!");
			}
		});
		
		
		
		
		Thread threadTwo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countdownLatch.countDown();
				}
				System.out.println("child threadTwo over!");
			}
		});
		
		
		threadOne.start();
		threadTwo.start();
		
		System.out.println("wait all child thread over!");
		
		
		countdownLatch.await();
		
		System.out.println("all child thread over!");
	}
	

}
