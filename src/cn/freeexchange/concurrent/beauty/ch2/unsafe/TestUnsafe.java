package cn.freeexchange.concurrent.beauty.ch2.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class TestUnsafe {
	
	static final Unsafe unsafe;
	
	static final long stateOffset;
	
	private volatile long state = 0;
	
	
	static {
		try {
			//使用反射获取Unsafe的成员变量theUnsafe
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			
			//设置为可存取
			field.setAccessible(true);
			
			//获取该变量的值
			unsafe = (Unsafe) field.get(null);
			
			//获取state变量在类TestUnsafe中的偏移值
			stateOffset = 
					unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			throw new Error(ex);
		}
	}
	
	public static void main(String[] args) {
		TestUnsafe test = new TestUnsafe();
		//(2.2.6)
		boolean success = 
				unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
		
		System.out.println(success);
		
	}	
}
