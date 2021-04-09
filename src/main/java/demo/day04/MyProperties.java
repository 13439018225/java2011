package main.java.demo.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 操作 .propertites (key-value)形式的文件
 * @author Administrator
 */
public class MyProperties {
	
	public static void main(String[] args) {
		//read("E:\\rcieWorkSpace\\java2011\\src\\main\\java\\demo\\day04\\test.properties");
		//write("E:\\rcieWorkSpace\\java2011\\src\\main\\java\\demo\\day04\\test.properties");
		test01();
	}
	
	//读
	public static void read(String file){
		//创建了一个操作文件的对象
		Properties pro=new Properties();
		//加载要读取的文件
		try {
			pro.load(new FileReader(file));
			//读取文件中的属性值
			String classnamevalue=pro.getProperty("classname");
			String interfacenamevalue=pro.getProperty("interfacename");
			System.out.println(classnamevalue+","+interfacenamevalue);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//写
	public static void write(String file){
		//创建了一个操作文件的对象
		Properties pro=new Properties();
		//加载要读取的文件
		try {
			pro.load(new FileReader(file));
			//通过 k-v 形式写文件
			pro.setProperty("name", "冯德智");
			pro.setProperty("sex", "男");
			//保存
			pro.store(new FileWriter(file), "注释....");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 把ArrayList集合中的字符串数据存储到文本文件
                    从文本文件中读取数据(每一行为一个字符串数据)到集合中，并遍历集合
	 */
	public static void test01(){
		List<User> list=new ArrayList<User>();
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("E:\\a.txt"));
			BufferedReader in=new BufferedReader(new FileReader("E:\\a.txt"));
			for(int i=0;i<5;i++){
				User user=new User("用户"+i,i);
				list.add(user);
				//写到文件中
				wirte(out,user.toString());
			}
			//关闭out
			if(out!=null){
				out.close();
			}
			
			//读文件内容到集合
			read(in);
			if(in!=null){
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void wirte(BufferedWriter out,String str){
		try {
			out.write(str);
			out.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void read(BufferedReader in){
		String str=null;
		List<User> templist=new ArrayList<User>();
		try {
			while((str=in.readLine())!=null){
				//name=用户0, age=0
				String name=str.substring(str.indexOf("=")+1, str.indexOf(","));
				String sage=str.substring(str.lastIndexOf("=")+1, str.length());
				User u=new User(name,Integer.parseInt(sage));
				templist.add(u);
			}
			for(User u:templist){
				System.out.println(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void a(){
		String s="name=用户0, age=0";
		int k=s.indexOf("=");
		int n=s.lastIndexOf("=");
		
		System.out.println(k+","+n); //4,13
	}
}
