package main.java.demo.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * 字符流读写
 * @author Administrator
 *
 */
public class MyFileWriterAndReader {
	
	
	
	public static void main(String[] args) {
		//读文件
		//fileRead("E:\\Test.java");
		//写文件
		//fileWrite("E:\\xw.java");
		
		//fileCopy("E:\\springbootlog\\spring.log","E:\\a.log");
		
		//bufferRead("E:\\Test.java");
		//bufferWrite("E:\\xw.java");
		
		bufferFileCopy("E:\\springbootlog\\spring.log","E:\\a.log");
	}
	
	//字符流读文件
	public static void fileRead(String fileapth){
		File file=new File(fileapth);
		InputStreamReader reader=null;
		char[] c=new char[2];
		int k=0;
		try {
			reader=new FileReader(file);
			while((k=reader.read(c))!=-1){
				String s=new String(c,0,k);
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(reader,null);
		}
	}
	
	//字符流读文件
	public static void fileWrite(String fileapth){
		File file=new File(fileapth);
		OutputStreamWriter write=null;
		try {
			//write=new FileWriter(file,true);
			write=new FileWriter(file);
			String str="aaaaaaaaaaaa\n"
					+ "ssssssssss\n";
			
			//write.write(str.toCharArray());
			write.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,write);
		}
	}
	
	//文件复制
	public static void fileCopy(String srcFile,String descFile){
		InputStreamReader reader=null;
		OutputStreamWriter write=null;
		char[] c=new char[1024];
		int k=0;
		try {
			reader=new FileReader(new File(srcFile));
			write=new FileWriter(new File(descFile));
			while((k=reader.read(c))!=-1){
				write.write(c, 0, k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(reader,write);
		}
		
	}
	
	
	/**
	 * -------------缓存字符流-------------------
	 */
	public static void bufferRead(String filePath){
		BufferedReader reader=null;
		String str=null;
		try {
			reader=new BufferedReader(new FileReader(filePath));
			while((str=reader.readLine())!=null){
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(reader,null);
		}
	}
	//写文件
	public static void bufferWrite(String filePath){
		BufferedWriter writer=null;
		String str="中国123\n哈哈\nabc";
		try {
			writer=new BufferedWriter(new FileWriter(filePath));
			writer.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,writer);
		}
	}
	
	//缓存流复制文件
	@SuppressWarnings("unused")
	public static void bufferFileCopy(String srcPath,String descPath){
		BufferedReader reader=null;
		BufferedWriter writer=null;
		String str=null;
		int k=0;
		try {
			reader=new BufferedReader(new FileReader(srcPath));
			writer=new BufferedWriter(new FileWriter(descPath));
			while((str=reader.readLine())!=null){
				if(k>0){
					writer.newLine();
				}
				writer.write(str);
				k++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(reader,writer);
		}
	}
	
	public static void close(Reader reader,Writer writer){
		try {
			if(writer!=null){
				writer.close();
			}
			if(reader!=null){
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
