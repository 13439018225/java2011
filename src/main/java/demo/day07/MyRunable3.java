package demo.day07;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信
 *
 */
public class MyRunable3 {
	final static Lock lock=new ReentrantLock();
	static Condition c1=lock.newCondition();
	static Condition c2=lock.newCondition();
	private static volatile  int nextPrintWho = 1;
	
	public static void main(String[] args) {
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				for(int i=0;i<10;i++){
					try {
						if(nextPrintWho!=1){
							c1.await();
						}
						System.out.println("A");
						nextPrintWho=2;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						c2.signal();
					}
				}
				lock.unlock();
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				for(int i=0;i<10;i++){
					try {
						if(nextPrintWho!=2){
							c2.await();
						}
						System.out.println("B");
						nextPrintWho=1;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						c1.signal();
					}
				}
				lock.unlock();
			}
		});
		
		t1.start();
		t2.start();
	}

}
