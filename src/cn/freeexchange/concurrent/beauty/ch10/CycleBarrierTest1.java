package cn.freeexchange.concurrent.beauty.ch10;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycleBarrierTest1 {
	
	
	private static CyclicBarrier cycleBarrier = new CyclicBarrier(2,new Runnable() {
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread()+ "task1 merge result.");
		}
	});
	
	
	public static void main(String[] args) {
		//创建一个线程个数固定为2的线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		//将线程A添加到线程池
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread()+" task1-1");
					System.out.println(Thread.currentThread()+ " enter in barrier");
					cycleBarrier.await();
					System.out.println(Thread.currentThread()+"enter out barrier");
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread()+" task 1-2");
					System.out.println(Thread.currentThread()+" enter in barrier");
					cycleBarrier.await();
					System.out.println(Thread.currentThread()+" enter out barrier");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		//关闭线程池
		executorService.shutdown();
	}
	
}
