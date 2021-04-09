package demo.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定线程池
 * @author Administrator
 * 
 * 3 个线程 处理6个文件,每个线程处理2个文件
 * 
 *
 */
public class MynewFixedThreadPool {
	
	
	public static void main(String[] args) {
		//线程数
		int threads=3;
		//线程计数器
		CountDownLatch countdownlatch=new CountDownLatch(threads);
		//设置所有线程就绪的屏障
		CyclicBarrier Barrier=new CyclicBarrier(threads);
		
		MyTarsks mytarsks=new MyTarsks(countdownlatch,Barrier);
		
		//创建固定线程池
		ExecutorService newfixedthreadpool = Executors.newFixedThreadPool(threads);
		//将任务放大线程池
		Future<ConcurrentMap<Integer, Integer>> result=null;
		for(int i=1;i<=threads;i++){
			result = newfixedthreadpool.submit(mytarsks);
		}
		try {
			countdownlatch.await();
			
			ConcurrentMap<Integer, Integer> concurrentMap = result.get();
			/*Set<Integer> keySet = concurrentMap.keySet();
			for(Integer i:keySet){
				int v=concurrentMap.get(i);
			}*/
			
			Set<Entry<Integer, Integer>> entrySet = concurrentMap.entrySet();
			
			Iterator<Entry<Integer, Integer>> iterator = entrySet.iterator();
			System.out.println("总记录数="+mytarsks.sum);
			while(iterator.hasNext()){
				Entry<Integer, Integer> entry = iterator.next();
				int key=entry.getKey();
				int value=entry.getValue();
				
				System.out.println("文件"+key+".txt  记录数=="+value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class MyTarsks implements Callable<ConcurrentMap<Integer, Integer>>{
	
	CountDownLatch countdownlatch;
	CyclicBarrier barrier;
	
	public MyTarsks(CountDownLatch countdownlatch,CyclicBarrier barrier){
		this.countdownlatch=countdownlatch;
		this.barrier=barrier;
	}
	//并发集合
	ConcurrentMap<Integer, Integer> map=new ConcurrentHashMap<Integer, Integer>();
    //总记录数
	AtomicInteger sum=new AtomicInteger(0);
	@Override
	public ConcurrentMap<Integer, Integer> call() throws Exception {
		//线程名称
		String name=Thread.currentThread().getName();
		System.out.println(name+"到达....");
		//等待所有线程就绪
		barrier.await();
		if("pool-1-thread-1".equals(name)){
			System.out.println("--------------所有线程就绪，开始计算文件-----------------");
		}
		
		if("pool-1-thread-1".equals(name)){
			//读二个文件,统计记录数
			for(int i=1;i<=2;i++){
				int k=readFile("e:\\"+i+".txt");
				sum.getAndAdd(k);
				map.put(i, k);
			}
		}else if("pool-1-thread-2".equals(name)){
			//读二个文件,统计记录数
			for(int i=3;i<=4;i++){
				int k=readFile("e:\\"+i+".txt");
				sum.getAndAdd(k);
				map.put(i, k);
			}
		}else if("pool-1-thread-3".equals(name)){
			//读二个文件,统计记录数
			for(int i=5;i<=6;i++){
				int k=readFile("e:\\"+i+".txt");
				sum.getAndAdd(k);
				map.put(i, k);
			}
		}
		countdownlatch.countDown();
		return map;
	}
	
	//读文件，统计记录数
	public int readFile(String filePath) throws IOException{
		BufferedReader bf=null;
		String str=null;
		int k=0;
		try {
			bf=new BufferedReader(new FileReader(filePath));
			while((str=bf.readLine())!=null){
				k++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bf!=null){
				bf.close();
			}
		}
		return k;
	}
}
