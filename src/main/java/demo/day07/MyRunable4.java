package demo.day07;


/**
 * 线程通信 object wait 与notify
 *
 */
public class MyRunable4 {
	private static final Object obj=new Object();
	private static volatile  int nextPrintWho = 1;
	
	public static void main(String[] args) {
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(obj){
					for(int i=0;i<10;i++){
						try {
							if(nextPrintWho!=1){
								obj.wait();
							}
							System.out.println("A");
							nextPrintWho=2;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							obj.notify();
						}
					}
				}
				
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					for(int i=0;i<10;i++){
						try {
							if(nextPrintWho!=2){
								obj.wait();
							}
							System.out.println("B");
							nextPrintWho=1;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally{
							obj.notify();
						}
					}
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
