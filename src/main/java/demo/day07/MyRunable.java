package demo.day07;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现 Runable 接口
 *
 */
public class MyRunable implements Runnable{

	private  int count=100;//票数
	private  int a=0;//A 窗口卖出去的数量
	private  int b=0;//B 窗口卖出去的数量
	private  int c=0;//C 窗口卖出去的数量
	
	Lock lock=new ReentrantLock();
	//计算器 3表示线程的数量
	static CountDownLatch countdownlatch=new CountDownLatch(3);
	
	public static void main(String[] args) {
		
		MyRunable myrunable=new MyRunable();
		//构建线程对象
		Thread t1=new Thread(myrunable,"A");
		Thread t2=new Thread(myrunable,"B");
		Thread t3=new Thread(myrunable,"C");
		
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			countdownlatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("剩余票数=="+myrunable.count+",A窗口=="+myrunable.a+",B窗口=="+myrunable.b+",C窗口=="+myrunable.c);
	}

	@Override
	public void run() {
		sellTiket();
	}
	
	public void sellTiket(){
		String name=Thread.currentThread().getName();
		while(count>0){
			lock.lock();  //加锁
			if(count>0){
				count--;
				if("A".equals(name)){
					a++;
				}else if("B".equals(name)){
					b++;
				}else if("C".equals(name)){
					c++;
				}
				try {
					Thread.sleep(new Random().nextInt(10)*20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lock.unlock();//释放锁
		}
		countdownlatch.countDown();
	}
}
