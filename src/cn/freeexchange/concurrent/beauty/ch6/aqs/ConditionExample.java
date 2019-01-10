package cn.freeexchange.concurrent.beauty.ch6.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. lock.newCondition()的作用其实是new了一个在AQS内部声明的ConditionObject对象,ConditionObject是AQS的内部类,可以访问AQS内部的变量和方法
 * 		在每个条件变量内部都维护了一个条件队列,用来存放调用条件变量的await()方法时被阻塞的线程。
 * 2. 当线程调用条件变量的await()方法时,在内部会构造一个类型为Node.CONDITION的NODE节点,然后将节点插入条件队列末尾,之后当前线程会释放获取的锁。并被阻塞挂起。
 * 		这时候如果其他线程调用lock.lock()尝试获取锁,就会有一个线程获取到锁。如果获取到锁的线程调用了条件变量的await()方法,则该线程也会被放入条件变量的阻塞队列,
 * 		然后释放获取到的锁,在await()的方法处阻塞
 * 
 * 
 * 3. 当另外一个线程调用条件变量的signal方法时(必须先调用锁lock()方法获取锁),在内部会把条件队列里面队头的一个线程节点从条件队列里面移除并放入
 * 	AQS的阻塞队列里面,然后激活这个线程。
 * 
 * 
 * 
 * 
 * 
 * */
public class ConditionExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				
				try {
					System.out.println("begin wait");
					condition.await();
					System.out.println("end wait");
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		
		
		Thread threadTwo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				try {
					System.out.println("begin signal");
					condition.signal();
					System.out.println("end signal");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});
		
		
		threadOne.start();
		
		Thread.sleep(1000);
		
		threadTwo.start();
	}
	
	
}
