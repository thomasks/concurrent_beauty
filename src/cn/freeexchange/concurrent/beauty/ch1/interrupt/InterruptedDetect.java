package cn.freeexchange.concurrent.beauty.ch1.interrupt;

public class InterruptedDetect {
	
	public static void main(String[] args) throws InterruptedException {
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				//当前线程中断标志为true时退出循环,并且清除中断标志
				while(!Thread.currentThread().interrupted()) {
					
				}
				
				System.out.println("threadOne is interrupted:"+Thread.currentThread().isInterrupted());
				
			}
		});
		
		//启动线程
		threadOne.start();
		
		//设置中断标志
		threadOne.interrupt();
		
		threadOne.join();
		System.out.println("main thread is over.");
		
	}
}
