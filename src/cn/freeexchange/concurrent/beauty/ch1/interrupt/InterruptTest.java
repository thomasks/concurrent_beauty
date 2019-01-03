package cn.freeexchange.concurrent.beauty.ch1.interrupt;

/**
 * Java 线程中断是一种线程间的协作模式,通过设置线程的中断标志并不能直接终止该线程的执行,
 * 而是被中断的线程根据中断状态自行处理
 * 
 *  interrupt()方法:中断线程,设置线程的中断标志为true,并立即返回。
 *  设置标志仅仅是设置标志,线程A实际并没有被中断,它会继续往下执行。
 *  如果线程因调用了wait()系列的函数,join()方法或者sleep()方法而被阻塞挂起
 *  此时，若该线程又被调用interrupt()方法,那么线程会在调用上述方法的地方抛出InterruptedException异常而返回
 *  
 *  
 *  isInterrupted():检测当前线程是否被中断,如果是返回true.否则返回false
 *  
 *  
 *  interrupt():  检测当前线程是否被中断,如果是返回true,否则返回false.
 *  与isInterrupted()不同的是,该方法如果发现线程被中断,则会清除中断标志,
 *  并且该方法是static方法,可以被Thread类直接调用。
 *  另外,在interrupted()内部是获取当前调用线程的中断标志,而不是调用interrupted()方法的实例对象的中断标志。
 *  
 *  
 * */
public class InterruptTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new Runnable() {
			
			//当前线程被中断则退出循环
			@Override
			public void run() {
				
				while(!Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread()+" hello");
				}
			}
		});
		
		
		//启动子线程
		thread.start();
		
		//主线程休眠1秒,以便中断前让子线程输出
		Thread.sleep(1000);
		
		//中断子线程
		System.out.println("main thread intrrupt thread");
		thread.interrupt();
		
		//等待子线程执行完毕
		thread.join();
		System.out.println("main is over");
		
		
		
		
		
		
	}
	

}
