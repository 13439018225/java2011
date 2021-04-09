package demo.day09;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 */
public class MyTheradPoolExecutor {
    /**
     * corePoolSize 初始化核心线程数
     * maximumPoolSize 最大线程数
     * keepAliveTime  新加入线程存活时间
     * unit 单位
     * 
	    TimeUnit.DAYS;              //天
		TimeUnit.HOURS;             //小时
		TimeUnit.MINUTES;           //分钟
		TimeUnit.SECONDS;           //秒
		TimeUnit.MILLISECONDS;      //毫秒
		TimeUnit.MICROSECONDS;      //微妙
		TimeUnit.NANOSECONDS;       //纳秒
		
     *  workQueue            任务队列 一般由下面3种类型
  
		   1)ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
		　　 2)LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
		　　 3)synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务
		
		
     *  handler 
     *  
     *  当拒绝处理任务时的策略，有以下四种取值
	 *  ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
		ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
		ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
		ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，他会自动重复调用execute()方法 
     * 
     */
	public static void main(String[] args) {
		
		//构建线程池
		/*ThreadPoolExecutor executor=
				new java.util.concurrent.ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(5), new ThreadPoolExecutor.CallerRunsPolicy());*/
		
		ThreadPoolExecutor executor=
				new java.util.concurrent.ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(5), new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("有任务被拒绝了....");
						if (!executor.isShutdown()) {
							r.run();
			            }
					}
				});
		int n=20;
		//提交n个任务，到线程池，由线程池中的线程去执行
		for(int i=0;i<n;i++){
			final int index=i;
			//构建一个执行任务的对象
			myRunnable myrunnable=new myRunnable(index);
			//将要执行的任务放到线程池
			executor.execute(myrunnable);
			System.out.println("线程池中的数量=="+executor.getPoolSize()+","
					+ "队列中等待执行的任务数目=="+executor.getQueue().size()+","
					+ "已执行完毕任务=="+executor.getCompletedTaskCount());
		}
		//关闭线程池
		executor.shutdown();
	}
}


class myRunnable implements Runnable{
	int index;

	public myRunnable(int index) {
		this.index = index;
	}


	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+"正在执行任务"+index);
            Thread.currentThread().sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("任务 "+index+"执行完毕");
	}
	
}
