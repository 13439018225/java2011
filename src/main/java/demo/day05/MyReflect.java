package demo.day05;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射
 * @author Administrator
 *
 */
public class MyReflect {
	
	public static void main(String[] args) {
		MyReflect ref=new MyReflect();
		ref.test04();
	}

	/**
	 * 如何获取Class类型的对象
	 */
	public void test01(){
		//1、全限定类名称   推荐
		/*try {
			Class<?> clazz=Class.forName("demo.day05.Stdent");
			//clazz 就是Student类的字节码文件对象
			Stdent st=(Stdent)clazz.newInstance();
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//2、类.class
		/*Class<?> clazz=Stdent.class;
		Stdent st;
		try {
			st = (Stdent)clazz.newInstance();
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}*/ 
		
		//应用.getClass()  
		/*Stdent st=new Stdent();
		try {
			Class<?> clazz=st.getClass();
			Stdent stt=(Stdent)clazz.newInstance();
			System.out.println(stt);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	/**
	 * 反射获取构造方法
	 */
	public void test02(){
		try {
			Class<?> clazz=Class.forName("demo.day05.Stdent");
			//取修饰符是public 的构造方法
			//Constructor<?>[] constructors=clazz.getConstructors();
			//取任意修饰的构造方法
			Constructor<?>[] constructors=clazz.getDeclaredConstructors();
			/*for(Constructor<?> c:constructors){
				System.out.println("构造方法名称==="+c.getName()+"参数个数=="+c.getParameterCount());
			}*/
			
			//通过构造方法创建对象
			/*constructors[0].setAccessible(true);
			Stdent st=(Stdent)constructors[0].newInstance();*/
			
			//调用有参数的构造方法，创建对象
			Stdent st=(Stdent)constructors[1].newInstance("冯德智",20);
			System.out.println(st);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过反射获取类的变量
	 */
	public void test03(){
		try {
			Class<?> clazz=Class.forName("demo.day05.Stdent");
			Constructor<?> c=clazz.getDeclaredConstructor();
			c.setAccessible(true);
			Stdent st=(Stdent)c.newInstance();
			//获取所有修饰符是public 的变量
			//Field[] fileds=clazz.getFields();
			//获取所有修饰符类型的变量
			Field[] fileds=clazz.getDeclaredFields();
			for(Field filed:fileds){
				String name=filed.getName();
				System.out.println("修饰符=="+Modifier.toString(filed.getModifiers())+",变量名称=="+filed.getName()+",数据类型=="+filed.getType());
				if("name".equals(name)){
					//给指定对象的变量赋值
					filed.set(st, "冯德智");
				}else if("age".equals(name)){
					filed.setAccessible(true);
					filed.set(st, 20);
				}
			}
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过反射获取方法
	 */
	public void test04(){
		try {
			Class<?> clazz=Class.forName("demo.day05.Stdent");
			//有参数的构造方法
			Constructor constructor=clazz.getConstructor(String.class,int.class);
			Stdent st=(Stdent)constructor.newInstance("李经理",20);
			//获取所有方法
			/*Method[] methods=clazz.getDeclaredMethods();
			for(Method method:methods){
				System.out.println("修饰符=="+Modifier.toString(method.getModifiers())+","
						+ "方法名称=="+method.getName()+",返回值类型=="+method.getReturnType()+","
								+ "参数个数=="+method.getParameterCount());
				//方法名称
				String name=method.getName();
				if("setName".equals(name)){
					//调用setName方法
					method.invoke(st, "徐伟");
				}
			}*/
			//指定调用某个方法
			Method method=clazz.getMethod("setName", String.class);
			method.invoke(st, "张一豪");
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}



class Stdent{
	public String name;
	private int age;
	
	private Stdent(){
		
	}
	
	public Stdent(String name,int age){
		this.name=name;
		this.age=age;
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
		return "Stdent [name=" + name + ", age=" + age + "]";
	}
}