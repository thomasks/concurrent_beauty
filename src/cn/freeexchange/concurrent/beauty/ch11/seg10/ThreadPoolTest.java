package cn.freeexchange.concurrent.beauty.ch11.seg10;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	
	static class LocalVariable {
		private Long[] a = new Long[1024*1024];
		
		//(1)
		final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
				5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
		
		
		//(2)
		final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();
		
		
		public static void main(String[] args) throws InterruptedException {
			
			
			for(int i=0;i<50;++i) {
				poolExecutor.execute(new Runnable() {
					
					@Override
					public void run() {
						localVariable.set(new LocalVariable());
						System.out.println("use local variable");
					}
				});
				
				Thread.sleep(1000);
			}
			
			
			System.out.println("pool executor over");
		}
	}
	
}
