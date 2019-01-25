package cn.freeexchange.concurrent.beauty.ch11.seg9;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	
	//线程池单个线程,线程池队列元素个数为1
	private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1,
			1, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1),
			new ThreadPoolExecutor.DiscardPolicy());
	
	
	public static void main(String[] args) throws Exception {
		
		Future<?> futureOne = executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("start runable one");
				try {
					Thread.sleep(5000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Future<?> futureTwo = executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("start runnable two");
			}
		});
		
		
		Future futureThree = null;
		try {
			futureThree = executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("start runnable three.");
				}
			});
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		
		System.out.println("task one "+ futureOne.get());
		System.out.println("task two "+ futureTwo.get());
		System.out.println("task three "+ (futureThree ==null ? null :futureThree.get()));
		
		executorService.shutdown();
	}
	

}
