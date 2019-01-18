package cn.freeexchange.concurrent.beauty.ch7;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue并发队列是一个无界阻塞延迟队列,队列中的每个元素都有个过期时间,当从队列获取元素时,只有过期元素才会出队列。
 * 队列头元素是最快要过期的元素。
 * */
public class DelayQueueExample {
	
	static class DelayedEle implements Delayed {
		
		private final long delayTime;	//延迟时间
		
		private final long expire;	//到期时间
		
		private String taskName;	//任务名称
		
		
		public DelayedEle(long delay,String taskName) {
			this.delayTime = delay;
			this.taskName = taskName;
			this.expire = System.currentTimeMillis() + delay;
		}


		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(this.expire -System.currentTimeMillis(),
					TimeUnit.MILLISECONDS);
		}

		@Override
		public int compareTo(Delayed o) {
			return (int) (this.getDelay(TimeUnit.MILLISECONDS) - 
					o.getDelay(TimeUnit.MILLISECONDS));
		}


		@Override
		public String toString() {
			return "DelayedEle [delayTime=" + delayTime + ", expire=" + expire + ", taskName=" + taskName + "]";
		}
		
	}
	
	
	public static void main(String[] args) {
		DelayQueue<DelayedEle> delayQueue = new DelayQueue<>();
		
		Random random  = new Random();
		for(int i=0;i<10;++i) {
			DelayedEle element = new DelayedEle(random.nextInt(500), "task:"+i);
			delayQueue.offer(element);
		}
		
		DelayedEle ele = null;
		try {
			for(;;) {
				while((ele = delayQueue.take()) != null) {
					System.out.println(ele.toString());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
