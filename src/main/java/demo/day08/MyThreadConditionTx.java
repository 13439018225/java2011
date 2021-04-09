package demo.day08;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信
 *
 */
public class MyThreadConditionTx {
	
	//声明变量控制执定线程执行
    private  static volatile int k=1;
    //锁对象
    private static final Lock lock=new ReentrantLock();
    //创建4个条件
    private static Condition c1=lock.newCondition();
    private static Condition c2=lock.newCondition();
    private static Condition c3=lock.newCondition();
    private static Condition c4=lock.newCondition();
    
    //计算器
    private static CountDownLatch countdownlatch=new CountDownLatch(4);
	
	public static void main(String[] args) {
		
		Timer time=new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						lock.lock();
						try {
							if(k!=4){
								c4.await();
							}
							System.out.println("唯见长江天际流");
							k=1;
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							c1.signal();
							lock.unlock();
							countdownlatch.countDown();
						}
					}
				}).start();
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						
						lock.lock();
						try {
							if(k!=3){
								c3.await();
							}
							System.out.println("孤帆远影碧空尽");
							k=4;
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							c4.signal();
							lock.unlock();
							countdownlatch.countDown();
						}
						
					}
				}).start();
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						lock.lock();
						try {
							if(k!=2){
								c2.await();
							}
							System.out.println("烟花三月下扬州");
							k=3;
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							c3.signal();
							lock.unlock();
							countdownlatch.countDown();
						}
					}
				}).start();
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						lock.lock();
						try {
							if(k!=1){
								c1.await();
							}
							System.out.println("故人西辞黄鹤楼");
							k=2;
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							c2.signal();
							lock.unlock();
							countdownlatch.countDown();
						}
					}
				}).start();
				
				try {
					countdownlatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(countdownlatch.getCount()==0){
					System.out.println("---------------------");
					break;
				}
			}
		}, 2000, 1000);
	}

}
