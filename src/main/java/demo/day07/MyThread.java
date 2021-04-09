package demo.day07;

import java.util.Random;

/**
 * 
 * 继承 Thread类
 *
 */
public class MyThread extends Thread{
	
	private static int count=100;//票数
	private static int a=0;//A 窗口卖出去的数量
	private static int b=0;//B 窗口卖出去的数量
	private static int c=0;//C 窗口卖出去的数量
	
	
	public static void main(String[] args) {
		//开启多线程
		MyThread t1=new MyThread();
		MyThread t2=new MyThread();
		MyThread t3=new MyThread();
		//设置线程的名称
		t1.setName("A");
		t2.setName("B");
		t3.setName("C");
		//给线程设置优先级,默认优先级是5 
		/*t1.setPriority(9);
		t2.setPriority(1);*/
		//让线程处于就绪状态,抢到cpu的执行权处于运行状态
		t1.start();
		t2.start();
		t3.start();
		
		//线程加入
		try {
			t1.join();  //让其它线程等待t1执行完毕，在执行
			t2.join();  //让其它线程等待t2执行完毕，在执行
			t3.join();  //让其它线程等待t3执行完毕，在执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		/*while(Thread.activeCount()>1){
			
		}*/
		/*
		 * 不推荐,由于子线程什么时候执行完不太确定
		 * try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		System.out.println("剩余票数=="+count+",A窗口=="+a+",B窗口=="+b+",C窗口=="+c);
		
	}
	
	//线程执行的代码，放到run
	@Override
	public void run() {
		sellTiket();
	}
	
	public void  show(){
		String name=Thread.currentThread().getName();
		if("A".equals(name)){
			//让当前线程睡眠
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<100;i++){
			System.out.println("线程"+name+"执行第"+(i+1));
		}
	}
	
    //售票方法
	public void sellTiket(){
		String name=Thread.currentThread().getName();
		
		while(count>0){
			synchronized(MyThread.class){
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
			}
		}
		
		
	}
}
