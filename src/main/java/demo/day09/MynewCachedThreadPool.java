package demo.day09;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 
    newCachedThreadPool() : 创建一个大小无界的缓冲线程池，它的任务队列是一个同步队列，任务加入加入到池中，
                                                                                   如果池中有空闲线程， 则用空闲线程执行，如无则创建新线程执行，
                                                                                   池中的空闲线程超过60秒， 将被销毁释放， 线程数随任务的多少变化，适用于耗时较小的异步任务， 
                                                                                   池的核心线程数=0， 最大线程数=Integer.MAX_VALUE
                                                                                   
 */

public class MynewCachedThreadPool {
	// 线程数
    private static final int threads = 200;
    // 用于计数线程是否执行完成
    CountDownLatch countDownLatch = new CountDownLatch(threads);
    // 同时并发访问的线程数
    Semaphore semp = new Semaphore(40);
    // 设置屏障,等待所有线程到位
    CyclicBarrier barrier = new CyclicBarrier(threads);
  	
    
	public void test01(){
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for(int i=1;i<=threads;i++){
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					
					String name=Thread.currentThread().getName();
					System.out.println("用户"+name+"准备登陆游戏...");
					try {
						barrier.await();
						semp.acquire();
						//开始游戏
						play(name);
					} catch (Exception e) {
						e.printStackTrace();
					} finally{
						semp.release();
					}
					
				}
			});
		}
		cachedThreadPool.shutdown();
	}
	
	public void play(String name){
		
		System.out.println("用户"+name+" "+new Date().getTime()+":进入游戏...");
		try {
			int k=new Random().nextInt(20)*500;
			Thread.sleep(k);
			
			System.out.println("用户"+name+" "+new Date().getTime()+":退出游戏");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new MynewCachedThreadPool().test01();
	}
}


