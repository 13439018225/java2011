package main.java.demo.day01;

/**
冯德智修改  测试更新ok
*/
public class Test01 {
	public static void main(String[] args) {
		System.out.println(args[0]);
		System.out.println(args[1]);
		
		new zi().show();
	}
}
class Pseron{
	public String name="父亲";
	public int age;
	
    public Pseron(){
	}
    public Pseron(String name,int age){
		this.name=name;
		this.age=age;
	}
	@Override
	public String toString() {
		return "Pseron [name=" + name + ", age=" + age + "]";
	}
}
class zi extends Pseron{
	//String name="孩子";
	public void show(){
		String name="小孩子";
		System.out.println(name);
		System.out.println(this.name);
		System.out.println(super.name);
	}
}
