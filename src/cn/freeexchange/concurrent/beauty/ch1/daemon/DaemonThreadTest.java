package cn.freeexchange.concurrent.beauty.ch1.daemon;

/**
 * Java线程分为两类：
 * 	守护进程
 *  用户进程
 *  
 *  当最后一个非守护进程结束时,JVM会正常退出,而不管当前是否有守护进程
 * */
public class DaemonThreadTest {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;) {
					
				}
				
			}
		});
		
		//启动子线程
		thread.setDaemon(true);
		thread.start();
		
		
		System.out.println("main thread is over.");
	}
	

}
