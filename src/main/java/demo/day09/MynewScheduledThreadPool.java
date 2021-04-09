package demo.day09;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
    newScheduledThreadPool(int nThreads) : 能定时执行任务的线程池，该池的核心线程数由参数指定，最大线程数=Integer.MAX_VALUE
            由 ReentrantLock 与 Condition 配合使用，实现线程指定顺序执行
 */

public class MynewScheduledThreadPool {
	// 线程数
    private static final int threads = 4;
    // 用于计数线程是否执行完成
    CountDownLatch countDownLatch = new CountDownLatch(threads);
    // 同时并发访问的线程数
    Semaphore semp = new Semaphore(threads);
    // 设置屏障,等待所有线程到位
    CyclicBarrier barrier = new CyclicBarrier(threads);
  	
    ReadSong readSong=new ReadSong(barrier,countDownLatch);
    
	public void test01(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
		for(int i=1;i<=threads;i++){
			//initialDelay值1：初始化延时     delay值3：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
			scheduledThreadPool.scheduleWithFixedDelay(readSong, 1, 5, TimeUnit.SECONDS);
		}
		try {
			countDownLatch.await();
			//scheduledThreadPool.shutdown();
			System.out.println("cpu核心数:"+Runtime.getRuntime().availableProcessors());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MynewScheduledThreadPool().test01();
	}
}

class ReadSong implements Runnable{
	private CyclicBarrier barrier;
	private CountDownLatch countDownLatch;
	private static volatile  int nextPrintWho = 1;
	final Lock lock = new ReentrantLock();
	Condition c1=lock.newCondition();
	Condition c2=lock.newCondition();
	Condition c3=lock.newCondition();
	Condition c4=lock.newCondition();
	
	public ReadSong(CyclicBarrier barrier,CountDownLatch countDownLatch) {
		this.barrier = barrier;
		this.countDownLatch=countDownLatch;
	}

	@Override
	public void run() {
		try {
			String name=Thread.currentThread().getName();
			System.out.println(name+"到达,等待中...");
			barrier.await();
			
			lock.lock();
			if("pool-1-thread-4".equals(name)){
				m4();
			}else if("pool-1-thread-3".equals(name)){
				m3();
			}else if("pool-1-thread-2".equals(name)){
				m2();
			}else if("pool-1-thread-1".equals(name)){
				m1();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			countDownLatch.countDown();
			lock.unlock();
		}
	}
	/**
	 * 当前线程调用condition.await()方法后，会使得当前线程释放lock然后加入到等待队列中，
	 * 直至被signal/signalAll后会使得当前线程从等待队列中移至到同步队列中去，
	 * 直到获得了lock后才会从await方法返回，或者在等待时被中断会做中断处理
	 */
	public void m1(){
		try {
			if(nextPrintWho!=1){
				c1.await();//等待，并且释放锁
			}
			System.out.println("李白乘舟将欲行");
			Thread.sleep(1000);
			nextPrintWho=2;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			c2.signal();
		}
	}
	public void m2(){
		try {
			if(nextPrintWho!=2){
				c2.await();
			}
			System.out.println("忽闻岸上踏歌声");
			Thread.sleep(1000);
			nextPrintWho=3;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			c3.signal();
		}
	}
	public void m3(){
		try {
			if(nextPrintWho!=3){
				c3.await();
			}
			System.out.println("桃花潭水深千尺");
			Thread.sleep(1000);
			nextPrintWho=4;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			c4.signal();
		}
	}
	public void m4(){
		try {
			if(nextPrintWho!=4){
				c4.await();
			}
			System.out.println("不及汪伦送我情");
			Thread.sleep(1000);
			nextPrintWho=1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			c1.signal();
		}
	}
	
}

