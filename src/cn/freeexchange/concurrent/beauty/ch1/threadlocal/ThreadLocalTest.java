package cn.freeexchange.concurrent.beauty.ch1.threadlocal;

public class ThreadLocalTest {
	
	static ThreadLocal<String> localVariable = new ThreadLocal<>();
	
	
	//(1) print函数
	static void print(String str) {
		
		//1.1 打印当前线程本地内存中localVariable变量的值
		System.out.println(str+":"+localVariable.get());
		
		//1.2 清除当前线程本地内存中的localVariable变量
		localVariable.remove();
		
	}
	
	
	public static void main(String[] args) {
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//3.1 设置线程One中本地变量localVariable的值
				localVariable.set("threadOne local variable");
				print("threadOne");
				System.out.println("threadOne remove after"+":"+localVariable.get());
			}
		});
		
		Thread threadTwo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				localVariable.set("threadTwo local variable");
				print("threadTwo");
				//打印本地变量值
				System.out.println("threadTwo remove after"+":"+localVariable.get());
			}
		});
		
		threadOne.start();
		threadTwo.start();
	}

}
