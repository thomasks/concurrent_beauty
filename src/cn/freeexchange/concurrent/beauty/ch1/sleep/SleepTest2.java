package cn.freeexchange.concurrent.beauty.ch1.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 与wait()方法不同,调用sleep方法时,并不释放监视器锁
 * */
public class SleepTest2 {
	
	private static final Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		
		
		Thread threadA = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//获取独占锁
				lock.lock();
				try {
					System.out.println("child ThreadA is in sleep");
					Thread.sleep(10000);
					System.out.println("child ThreadA is in awaked");
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//获取独占锁
				lock.lock();
				try {
					System.out.println("child ThreadB is in sleep");
					Thread.sleep(10000);
					System.out.println("child ThreadB is in awaked");
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		
		
		threadA.start();
		threadB.start();
	}
}
