package demo.day08;

import java.util.concurrent.CountDownLatch;

/**
 * 线程可见性
 * 
 * volatile  (1)线程可见性
 *           (2)不能保证原子性        
 *
 */
public class MyVolatile{
	private static volatile boolean f=false;
	
	public static void main(String[] args) {
		
		CountDownLatch countdownlatch =new CountDownLatch(2);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//确保t2开始执行循环
					Thread.sleep(200);
					f=true;
					System.out.println("线程t1执行完毕...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					countdownlatch.countDown();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
                while(!f){
				}
				System.out.println("线程t2执行完毕...");
				countdownlatch.countDown();
			}
		}).start();
		
		try {
			countdownlatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程执行完毕...");
	}

}
