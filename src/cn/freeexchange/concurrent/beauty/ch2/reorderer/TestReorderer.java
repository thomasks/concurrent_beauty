package cn.freeexchange.concurrent.beauty.ch2.reorderer;

public class TestReorderer {
	
	private static int num =0;
	private static boolean ready = false;
	
	
	
	public static class ReadThread extends Thread {

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				if(ready) {
					System.out.println(num+num);
				}
				System.out.println("read thread...");
			}
		}
		
	}
	
	public static class WriterThread extends Thread {
		public void run() {
			num =2;
			ready = true;
			System.out.println("writeThread set over...");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReadThread rt = new ReadThread();
		rt.start();
		
		WriterThread wt = new WriterThread();
		wt.start();
		
		Thread.sleep(10);
		rt.interrupt();
		
		System.out.println("main exit.");
		
	}

}
