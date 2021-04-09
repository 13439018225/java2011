package demo.day09;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class Test02 {
	static int k=1;
	public static void main(String[] args) {
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				String name=Thread.currentThread().getName();
				for(int i=1;i<=10;i++){
					if(i==5){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(name+"---线程休眠...----");
					}
					System.out.println(name+"---线程执行第"+i+"次");
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String name=Thread.currentThread().getName();
				for(int i=1;i<=10;i++){
					if(i==8){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(name+"---线程休眠...----");
					}
					System.out.println(name+"---线程执行第"+i+"次");
				}
			}
			
		}).start();*/
		shlude();
	}
	
	public static void shlude(){
		Timer time=new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				//定时开启线程
				CountDownLatch count=new CountDownLatch(2);
				T t=new T(count);
				Thread t1=new Thread(t,"0");
				Thread t2=new Thread(t,"1");
				
				t2.start();
				t1.start();
				
				try {
					count.await();
					System.out.println("---------"+(k++)+"次-----------");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}, 2000, 4000);
	}
}
class T implements Runnable{

	private final static Object obj=new Object();
	
	CountDownLatch count=null;
	
	public T(CountDownLatch count){
		this.count=count;
	}
	
	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		
		for(int i=1;i<=10;i++){
			synchronized (obj) {
				if("0".equals(name)){
					if(i==2){
						obj.notify();
						try {
							obj.wait();
							System.out.println(name+"---线程休眠----");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				if("1".equals(name)){
					if(i==5){
						obj.notify();
						try {
							obj.wait();
							System.out.println(name+"---线程休眠----");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				if(i==10){
					obj.notify();
				}
				System.out.println(name+"---线程执行第"+i+"次");
			}
		}
		count.countDown();
	}
	
}
