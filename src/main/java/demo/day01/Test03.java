package main.java.demo.day01;

public class Test03 {
	
	private static Test03 t3 = new Test03();
	 
	public static int count1;
    public static int count2 = 0;
    
    private Test03() {
        count1++;
        count2++;
        System.out.println("构造方法...");
    }
    public static Test03 getStaticLoadInstance(){
        return t3;
    }
	public static void main(String[] args) {
		Test03 staticLoad = Test03.getStaticLoadInstance();

        System.out.println("count1 = " + staticLoad.count1);
        System.out.println("count2 = " + staticLoad.count2);
	}
}

class B{
	int code=100;
	public B(){
		System.out.println("B的构造方法...");
	}
}