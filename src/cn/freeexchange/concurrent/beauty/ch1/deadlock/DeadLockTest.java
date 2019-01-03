package cn.freeexchange.concurrent.beauty.ch1.deadlock;


/**
 * 死锁产生的4个条件
 * 	互斥条件
 * 请求并持有条件
 * 不可剥夺条件
 * 环路等待条件
 * 
 * 
 * 有序的获取资源是避免死锁的有效手段
 * 
 * 	
 * */
public class DeadLockTest {
	
	private static Object resourceA = new Object();
	
	private static Object resourceB = new Object();
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread threadA = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				synchronized (resourceA) {
					System.out.println(Thread.currentThread()+" get ResourceA");
					
					try {
						Thread.sleep(1000);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread()+"waiting get ResourceB");
					
					
					synchronized (resourceB) {
						System.out.println(Thread.currentThread()+"get resourceB");
					}
				}
			}
		});
		
		
		Thread threadB = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized(resourceB) {
					System.out.println(Thread.currentThread()+" get ResourceB");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread()+"waiting get ResourceA");
					synchronized (resourceA) {
						System.out.println(Thread.currentThread()+"get resourceA");
					}
				}
				
				
				
			}
		});
		
		
		//启动线程
		threadA.start();
		threadB.start();
		
	}
	
	
}
