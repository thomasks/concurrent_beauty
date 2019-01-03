package cn.freeexchange.concurrent.beauty.ch1.sleep;

/**
 * 在睡眠期间,如果其他线程调用了该线程的interrupt()方法中断了该线程,
 * 则该线程会在调用sleep()方法的地方抛出InterruptedException
 * */
public class SleepInterruptTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("child thread is in sleep");
					Thread.sleep(10000);
					System.out.println("child thread is in awaked");
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		//启动线程
		thread.start();
		
		//主线程休眠两秒
		Thread.sleep(2000);
		
		//主线程中断子线程
		thread.interrupt();
		
	}

}
