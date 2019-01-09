package cn.freeexchange.concurrent.beauty.ch6.aqs;

/**
 * 1. AQS是一个双向队列,通过head和tail记录队首和队尾元素,队列元素的类型为Node
 * 2. Node中的thread变量用来存放进入AQS队列里面的线程
 * 3. Node节点内部的SHARED用来标记该线程是获取共享资源时被阻塞挂起后放入AQS队列的,EXCLUSIVE用来标记线程是获取独占资源时被挂起后放入AQS队列的
 * 4. waitStatus记录当前线程等待状态,可以为CANCELLED(线程被取消了)、SIGNAL(线程需要被唤醒)、CONDITION(线程在条件队列里等待)、PROPAGATE(释放共享资源时,需要通知其他节点)
 * 5. prev记录当前节点的前驱节点,next记录当前节点的后续节点
 * 6. 在AQS中维持了一个单一的状态信息state,可以通过setState、getState、compareAndSetState函数修改其值。、
 * 7. 对于ReentrantLock的实现来说,state可以用来表示当前线程获取锁的可重入次数
 * 8. 对于ReentrantReadWriteLock来说,state的高16位表示读状态,也就是获取该读锁的次数,低16位表示获取到写锁的先后才能的可重入次数
 * 9. 对于semaphore来说,state用来表示当前可用信号的个数
 * 10.对于CountDownLatch来说,state用来表示计数器当前的值
 * 11. ConditionObject,用来结合锁实现线程同步。ConditionObject可以直接访问AQS对象内部的变量，比如state状态值和AQS队列。
 * 12. ConditionObject是条件变量,每个条件变量对应一个条件队列(单向链表队列),其用来存放调用条件变量的await方法后被阻塞的线程，如类图所示,这个条件队列的头、尾元素分别为firstWaiter和lastWaiter。
 * 13. 使用独占方式获取的资源是与具体线程绑定的,就是说如果一个线程获取到了资源,就会标记是这个线程获取到了,
 * 	        其他线程再尝试操作state获取资源时会发现当前该资源不是自己持有的,就会在获取失败后阻塞。
 * 	       比如,独占锁ReentrantLock的实现,当一个线程获取了ReentrantLock的锁后,在AQS内部会首先使用CAS操作吧state状态值从0变为1,然后设置当前锁的持有者为当前线程,
 *    当该线程再次获取锁时发现他就是锁的持有者,则会把state从1改为2,也就是设置可重入次数,而另外一个线程获取锁时发现自己并不是该锁的持有者就会被放入AQS阻塞队列后挂起。
 * 
 * 14. 
 * 	  
 * 
 * */
public class AQSExample {
	
}
