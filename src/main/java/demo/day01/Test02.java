package main.java.demo.day01;

public class Test02 {
	
	private static int count;
	
	private int num;
	
	public static void main(String[] args) {
		Test02 t1=new Test02();
		t1.count=10;
		t1.num=10;
		
		Test02 t2=new Test02();
		t2.count=20;
		t2.num=20;
		
		System.out.println(t1.count+","+t2.count);
		System.out.println(t1.num+","+t2.num);
		
	}

}
