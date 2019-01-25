package cn.freeexchange.concurrent.beauty.ch11.seg7;

public class NamedThreadApp {
	
	static final String THREAD_SAVE_ORDER = "THREAD_SAVE_ORDER";
	
	static final String THREAD_SAVE_ADDR = "THREAD_SAVE_ADDR";
	
	
	public static void main(String[] args) {
		//订单模块
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("保存订单的线程");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				throw new NullPointerException();
			}
			
			
		},THREAD_SAVE_ORDER);
		
		//发货模块
		Thread threadTwo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("保存收货地址的线程");
			}
		},THREAD_SAVE_ADDR);
		
		threadOne.start();
		threadTwo.start();
	}

}
