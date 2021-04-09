package main.java.demo.day04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 对象序列号 与 反序列化
 * @author Administrator
 * 
 * 
 *
 */
public class MyObjStream {
	
	public static void main(String[] args) {
		//write("E:\\user.txt");
		read("E:\\user.txt");
	}
	
	//对象序列化
	public static void write(String filename){
		ObjectOutputStream  out=null;
		try {
			out=new ObjectOutputStream(new FileOutputStream(filename));
			//写对象
			User user=new User();
			user.setName("刘备");
			user.setAge(20);
			out.writeObject(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//反序列化
	public static void read(String filename){
		ObjectInputStream  in=null;
		try {
			in=new ObjectInputStream(new FileInputStream(filename));
			//读序列号文件，转为对象
			Object obj=in.readObject();
			User user=(User)obj;
			user.setName("关羽");
			System.out.println(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class User implements Serializable{
	private String name;
	private int age;
	
	public User(){
		
	}
	public User(String n,int a){
		name=n;
		age=a;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "name=" + name + ", age=" + age;
	}
	
}
