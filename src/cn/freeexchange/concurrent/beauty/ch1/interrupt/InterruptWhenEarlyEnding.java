package cn.freeexchange.concurrent.beauty.ch1.interrupt;

public class InterruptWhenEarlyEnding {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("threadOne begin sleep for 2000 seconds");
					Thread.sleep(2000000);
					System.out.println("threadOne end sleep for 2000 seconds");
				} catch (InterruptedException e) {
					System.out.println("threadOne is interrupted while sleeping");
					e.printStackTrace();
				}
				
				System.out.println("threadOne leaving normally");
			}
		});
		
		//启动线程
		threadOne.start();
		
		//确保子线程进入休眠状态
		Thread.sleep(1000);
		
		//打断子线程的休眠状态,让子线程从sleep()函数返回
		threadOne.interrupt();
		
		//等待子线程执行完毕
		threadOne.join();
		
		System.out.println("main thread is over");
		
	}

}
