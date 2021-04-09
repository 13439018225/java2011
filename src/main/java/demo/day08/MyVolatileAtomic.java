package demo.day08;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不能保证原子性Atomic
 * 
 * 
 *
 */
public class MyVolatileAtomic {
	public volatile AtomicInteger n=new AtomicInteger(0);
	
	public static void main(String[] args) {
		mytimer();
	}

	
	
	public  static void mytimer(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				test();
			}
		}, 2000, 500);
	}
	
	public static void test(){
		int threads=10000;
		CountDownLatch countdownlatch=new CountDownLatch(threads);
		//ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(threads);
		MyVolatileAtomic mv=new MyVolatileAtomic();
		
		for(int j=0;j<threads;j++){
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						mv.n.getAndIncrement();
						
						countdownlatch.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}).start();
			//newFixedThreadPool.execute(t);
		}

		try {
			countdownlatch.await();
			
			if(countdownlatch.getCount()==0){
				//newFixedThreadPool.shutdown();
				System.out.println("n=="+mv.n);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
