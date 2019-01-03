package cn.freeexchange.concurrent.beauty.ch1.interrupt;


/**
 * isInterrupted()与interrupted()区别在于
 * 	1. interrupted()内部是获取当前线程的中断状态,与使用哪个对象调用无关
 *  
 * */
public class InterruptedTest {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;) {
					
				}
			}
		});
		
		//启动线程
		threadOne.start();
		
		//设置中断标志
		threadOne.interrupt();
		
		//获取中断标志
		System.out.println("isInterrupted:"+threadOne.isInterrupted());
		
		//获取中断标志并重置
		System.out.println("isInterrupted:"+threadOne.interrupted());
		
		//获取中断标志并重置
		System.out.println("isInterrupted:"+Thread.interrupted());
		
		//获取中断标志
		System.out.println("isInterrupted:"+threadOne.isInterrupted());
		
		threadOne.join();
		
		System.out.println("main thread is over");
		
	}

}
