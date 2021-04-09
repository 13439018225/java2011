package demo.day08;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程通信
 * @author Administrator
 * 
 * 打印 A-B,A-B,....A-B
 *
 */
public class MyThreadTx {
	 //锁
	 private  static final Object obj=new Object();
	 //声明变量控制执定线程执行
	 private  static volatile AtomicInteger k=new AtomicInteger(1);
	 
     public static void main(String[] args) {
		  Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(obj){
					for(int i=0;i<10;i++){
						try {
							while(k.get()!=1){
								//通过锁对象，让当前线程处于阻塞状态(等待状态),释放锁对象,在其它线程中通过锁对象调用notify
								//方法唤醒该线程
								obj.wait();
							}
							System.out.println("A");
							k.set(2);
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							//obj.notify();
							obj.notifyAll();
						}
					}
				}
			}
		  });
		  
		  Thread t2=new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized(obj){
						for(int i=0;i<10;i++){
							try {
								while(k.get()!=2){
									//通过锁对象，让当前线程处于阻塞状态(等待状态),释放锁对象,在其它线程中通过锁对象调用notify
									//方法唤醒该线程
									obj.wait();
								}
								System.out.println("B");
								k.set(3);
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}finally{
								obj.notifyAll();
								//obj.notify();
							}
						}
					}
				}
		  });
		  
		  Thread t3=new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized(obj){
						for(int i=0;i<10;i++){
							try {
								while(k.get()!=3){
									//通过锁对象，让当前线程处于阻塞状态(等待状态),释放锁对象,在其它线程中通过锁对象调用notify
									//方法唤醒该线程
									obj.wait();
								}
								System.out.println("C");
								k.set(1);
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}finally{
								obj.notifyAll();
								//obj.notify();
							}
						}
					}
				}
		  });
		  t1.start();
		  t2.start();
		  t3.start();
	 }
     
}
