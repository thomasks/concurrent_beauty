package cn.freeexchange.concurrent.beauty.ch6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FIFOMutex {
	
	private final AtomicBoolean locked = new AtomicBoolean(false);
	
	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();
	
	
	public void lock() {
		boolean wasInterrupted = false;
		Thread current = Thread.currentThread();
		waiters.add(current);
		
		//只有队首的线程可以获取锁
		while(waiters.peek() != current ||
				!locked.compareAndSet(false, true)) {
			LockSupport.park(this);
			if(Thread.interrupted()) {
				wasInterrupted = true;
			}
		}
		
		waiters.remove();
		
		if(wasInterrupted) 
			current.interrupt();
	}
	
	public void unlock() {
		locked.set(false);
		LockSupport.unpark(waiters.peek());
	}
	
}
