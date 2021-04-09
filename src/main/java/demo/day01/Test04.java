package main.java.demo.day01;
/**
 * 多态
 */
public class Test04 {
	public static void main(String[] args) {
		Master 徐伟=new Master();
		
		//创建一个狗的实例
		
		/**
		 * 变量 ：编译看左边，运行看左边
		 * 方法 ：编译看左边，运行看右边
		 */
		Animal animl=new Cat();
		//System.out.println(animl.name);
		
		//调用子类私有的方法
		Dog dog=(Dog)animl;
		dog.math();
		
		//徐伟.feed(animl);
	}
}
//饲养员
class Master{
	//喂动物
	public void feed(Animal animal){
		animal.eat();
	}
}
//动物
abstract class Animal{
	public abstract void eat();
}

interface IMath{
	public void math();
}

//狗
class Dog extends Animal implements IMath{
	String name="小狗";
	@Override
	public void eat(){
		System.out.println(name+"在吃东西。。。");
	}
	@Override
	public void math(){
		System.out.println("做算术...");
	}
}
//猫
class Cat extends Animal implements IMath{
	String name="小猫";
	@Override
	public void eat(){
		System.out.println(name+"在吃东西。。。");
	}
	@Override
	public void math(){
		System.out.println("做算术...");
	}
}

//猪
class Pig extends Animal{
	String name="小猪";
	@Override
	public void eat(){
		System.out.println(name+"在吃东西。。。");
	}
}
