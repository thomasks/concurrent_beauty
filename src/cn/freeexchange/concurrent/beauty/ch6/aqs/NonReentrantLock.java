package cn.freeexchange.concurrent.beauty.ch6.aqs;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class NonReentrantLock implements Lock, Serializable {
	
	
	private static class Sync extends AbstractQueuedLongSynchronizer {

		@Override
		protected boolean tryAcquire(long acquires) {
			assert acquires ==1;
			if(compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(long acquires) {
			assert acquires == 1;
			if(getState() == 0) 
				throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		Condition newCondition() {
			return new ConditionObject();
		}
		
	}
	
	private final Sync sync = new Sync();
	
	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
