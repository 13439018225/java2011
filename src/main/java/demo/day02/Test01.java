package main.java.demo.day02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Test01 {
	
	
	public static void main(String[] args) {
		test03();
	}
	
	public static void test01(){
		List<User> list=new ArrayList<User>();
		
		List<User> list2=new LinkedList<User>();
		
		list.add(new User("刘备",20));
		list.add(new User("张飞",10));
		
		User u=new User("刘备",20);
		if(!list.contains(u)){
			list.add(u);
		}
		for(User user:list){
			System.out.println(user);
		}
	}
	
	public static void test03(){
		int [] a={10,8,5,13,24,7};
		for(int j=1;j<=a.length-1;j++){
			for(int i=0;i<a.length-j;i++){
				if(a[i]<a[i+1]){
					int temp=a[i];
					a[i]=a[i+1];
					a[i+1]=temp;
				}
			}
		}
		
		for(int k:a){
			System.out.print(k+",");
		}
	}
	
	public static void test02(){
		Set<User> set=new HashSet<User>();
		set.add(new User("刘备",20));
		set.add(new User("张飞",10));
		
		User u=new User("刘备",20);
        if(!set.contains(u)){
        	set.add(u);
		}
		for(User user:set){
			System.out.println(user);
		}
	}
}

class User{
	private String name;
	
	private int age;
	
	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
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
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            User user = (User)obj;
            if(this.name.equals(user.name) && this.age==user.age){
            	return true;
            }
        }
        return false;
    }
	
	@Override
	public int hashCode() {
		return this.name.hashCode()^this.age;
	}
	
}
