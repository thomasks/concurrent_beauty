package cn.freeexchange.concurrent.beauty.ch6;

import java.util.concurrent.locks.LockSupport;


/**
 * 1. LockSupport是个工具类,它的主要作用是挂起和唤醒线程,该工具类是创建锁和其他同步类的基础
 * 2. LockSupport类与每个使用它的线程都会关联一个许可证,在默认情况下调用LockSupport类的方法线程是不持有许可证的。
 * 3. 如果调用park()方法的线程已经拿到了与LockSupport关联的许可类,则调用LockSupport.park()时会马上返回,否则调用线程会被禁止参与线程的调度,也就是会被阻塞挂起。
 * 		在其他线程调用unpark(Thread thread)方法并且将当前线程作为参数时,调用park()方法而被阻塞的线程会返回.
 * 		另外,如果其他线程调用了阻塞线程的interrupt()方法,设置了中断标志或者线程被虚假唤醒,则阻塞线程也会返回。
 * 		所以在调用park()方法时最好也使用循环条件判断
 * 
 * 4. 需要注意的是,因调用park()方法而被阻塞的线程被其他线程中断而返回时并不会抛出InterruptedException异常
 * 
 * */
public class LockSupportExample {
	
	
	public static void main(String[] args) {
		System.out.println("begin park!");
		
		
		//使当前线程获取到许可证
		LockSupport.unpark(Thread.currentThread());
		
		//再次调用park方法
		LockSupport.park();
		
		System.out.println("end park!");
	}

}
