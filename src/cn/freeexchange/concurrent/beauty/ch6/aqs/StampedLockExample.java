package cn.freeexchange.concurrent.beauty.ch6.aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock提供了三种模式的读写控制,
 * 		当调用获取锁的系列函数时,会返回一个long型的变量,我们称之为戳记(stamp),
 * 		这个戳记代表了锁的状态。其中try系列获取锁的函数,当获取锁失败后会返回为0的stamp值。
 * 		当调用释放锁和转换锁的方法时需要传入获取锁时返回的stamp的值.
 * 		
 * StampedLock提供的三种读写模式的锁分别如下:
 * 	*写锁writeLock: 是一个排它锁或者独占锁,某时只有一个线程可以获取该锁,当一个线程获取该锁后,其他请求读锁和写锁的线程必须等待。
 * 	这类似于ReentrantReadWriteLock的写锁(不同的是这里的写锁是不可重入锁);当目前没有线程持有读锁或者写锁时才可以获取到该锁。
 *  请求该锁成功后会返回一个stamp变量来表示该锁的版本,当释放该锁时需要调用unlockWrite方法并传递获取锁的stamp参数。并且它提供了非阻塞的tryWriteLock方法。
 *  
 *  *悲观读锁readLock:
 *  	是一个共享锁,在没有线程获取独占写锁的情况下,多个线程可以同时获取该锁.如果已经有线程持有写锁。则其他线程请求获取该读锁会被阻塞，这类似于ReentrantReadWriteLock的读锁
 *  	(不同的是,这里的读锁是不可重入锁)。这里所说的悲观是指在具体操作数据前,其会悲观的认为其他线程可能要对自己操作的数据进行修改,所以需要先对数据加锁,这是在读少写多的情况下的
 *  	一种考虑。请求该锁成功后会返回一个stamp变量用来表示该锁的版本,当释放该锁时需要调用unlockRead方法并传递stamp参数。并且它提供了非阻塞的tryReadLock方法。
 *  
 *  乐观读锁: 
 *  	它是相对于悲观锁来说的,在操作数据前并没有通过CAS设置锁的状态,仅仅通过位运算测试.如果当前没有线程持有写锁,则简单地返回一个非0的stamp版本信息。获取该stamp后在具体操作数据前,
 *  	还需要调用validate()方法验证该stamp是否已经不可用。也就是看调用tryOptimisticRead返回stamp后到当前时间期间是否有其他线程持有了写锁,如果是则validate会返回0,否则就
 *  	可以使用该stamp版本的锁对数据进行操作。由于tryOptimisticRead并没有使用CAS设置锁状态,所以不需要显式的释放该锁。该锁的一个特点是适用于读多写少的场景 ， 因为获取读锁只是
 *  	使用位操作进行检验，不涉及 CAS 操作，所以效率会高很多，但是同时由于没有使用真正的锁，在保证数据一致性上需要复制一份要操作的变量到方法钱，并且在操作数据时可能其他写线程己经修改了数据，
 *  而我们操作的是方法战里面的数据，也就是一个快照，所以最多返回 的不是最新的数据，但是一致性还是得到保障的 。
 * */
public class StampedLockExample {
	
	//成员变量
	private double x,y;
	
	//锁实例
	private final StampedLock sl = new StampedLock();
	
	//排他锁--写锁
	void move(double deltaX,double deltaY) {
		long stamp = sl.writeLock();
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			sl.unlockWrite(stamp);
		}
	}
	
	//乐观读锁(tryOptimisticRead)
	double distanceFromOrigin() {
		//尝试获取乐观锁
		long stamp = sl.tryOptimisticRead();
		
		// 将全部变量复制到方法体栈内
		double currentX =x,currentY = y;
		
		//检查在1)处获取了读锁戳记后,锁没有被其他线程排他性抢占
		if(!sl.validate(stamp)) {
			//如果被抢占则获取一个共享读锁(悲观获取)
			stamp = sl.readLock();
			try {
				//将全部变量复制到方法栈内
				currentX = x ;
				currentY = y;
			} finally {
				sl.unlockRead(stamp);
			}
		}
		
		//返回计算结果
		return Math.sqrt(currentX * currentX + currentY * currentY);
	}
	
	
	//使用悲观锁获取读锁,并尝试转换为写锁
	void moveIfAtOrigin(double newX,double newY) {
		//这里可以使用乐观锁替换
		long stamp = sl.readLock();
		try {
			//2) 如果当前点在原点则移动
			while (x==0.0 && y == 0.0) {
				//3)尝试将获取的读锁升级为写锁
				long ws = sl.tryConvertToWriteLock(stamp);
				//升级成功,则更新戳记,并设置坐标值,然后退出循环
				if (ws != 0L) {
					stamp = ws;
					x = newX;
					y= newY;
					break;
				} else {
					//读锁升级写锁失败,则释放读锁,显示获取独占写锁,然后循环重试
					sl.unlockRead(stamp);
					stamp = sl.writeLock();
				}
			}
		} finally {
			//释放锁
			sl.unlock(stamp);
		}
	}
	
	
	

}
