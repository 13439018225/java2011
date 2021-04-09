package demo.day07;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import demo.day05.EhDesign;
import demo.day05.LhDesign;

/**
 * 线程死锁问题
 * @author Administrator
 *
 */
public class MyRuable2{
	
	static String  s1="A";
	static String  s2="B";
	
	public static void main(String[] args) {
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				
				synchronized (s1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (s2) {
						System.out.println("线程1执行...");
					}
				}
			}
		},"A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (s2) {
					synchronized (s1) {
						System.out.println("线程2执行...");
					}
				}
			}
		},"B").start();*/
		
		
		int threads=30;
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threads);
		CyclicBarrier cyclicBarrier=new CyclicBarrier(threads);
		CountDownLatch countdown=new CountDownLatch(threads);
		for(int i=0;i<threads;i++){
			fixedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						//等待所有线程就绪,开始同时执行
						cyclicBarrier.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					demo.day05.EhDesign design=EhDesign.getInstance();
					System.out.println(design.hashCode());
					countdown.countDown();
				}
			});
		}
		
		try {
			countdown.await();
			fixedThreadPool.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
