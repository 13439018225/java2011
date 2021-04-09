package main.java.demo.day03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyFile {
	
	//构建文件对象
	public static void test01(){
		
		File file=new File("E:\\Test.java");
		/*File file2=new File("E:","Test.java");
		File file3=new File(new File("E:\\"),"Test.java");
		File file4=new File("E:");
		
		//判断是否为文件夹
		System.out.println(file4.isDirectory());
		System.out.println(file.isFile());
		
		//取文件的绝对路径
		System.out.println(file.getAbsolutePath());//
		System.out.println(file.getPath());
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(file.length());*/
		
		//创建文件夹
		File tempfile=new File("E:\\temp");
		//只能创建一级目录
		tempfile.mkdir();
		//创建多层目录
		/*tempfile=new File("e:\\a\\b\\c");
		tempfile.mkdirs();*/
		
		if(tempfile.exists()){
			//删除文件或文件夹
			tempfile.delete();
		}
		if(!tempfile.exists()){
			tempfile.mkdirs();
		}
		//创建文件
		File mytemfile=new File(tempfile,"a.txt");
		try {
			mytemfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//文件重命名  E:\\temp\\a.txt -->剪切并且重命名到E:\\下
		mytemfile.renameTo(new File("E:\\b.txt"));
	}
	
	//文件的遍历 filepath 要遍历文件的目录
	public static void test02(File file){
		String[] names={".txt",".java",".class",".png",".css",".js",".jpg",".html",".jsp"};
		if(file.isDirectory()){
			//找该文件下的所有文件夹或文件
			File[] files=file.listFiles();
			if(files.length>0){
				for(File f:files){
					//递归
					test02(f);
				}
			}
		}else{
			//是文件
			String filename=file.getName();
			for(String s:names){
				if(filename.endsWith(s)){
					System.out.println(file.getAbsolutePath());
				}else{
					continue;
				}
			}
		}
	}

	//读文件
	public static void readFile(String srcFile){
		//源文件对象
		File src=new File(srcFile);
		byte[] b=new byte[16];
		int n=0;
		InputStream in=null;
		try {
			//输入流对象
			in=new FileInputStream(src);
			while((n=in.read(b))!=-1){
				System.out.println(new String(b));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,in);
		}
	}
	
	//写文件
	public static void writeFile(String descFile){
		//要写入的文件
		File desc=new File(descFile);
		//构建一个输出流的对象，用于写操作
		OutputStream out=null;
		try {
			out=new FileOutputStream(desc);
			String str="【环球时报记者 李司坤 邢晓婧 李萌 高雷】“一边造谣抵制新疆棉花，一边又想在中国赚钱？\n"
					+ "痴心妄想！”24日，瑞典服装品牌H&M发表在官网上的一份声明在微博上广泛传播，引发中国网友愤怒。\n"
					+ "这份名为“H&M集团关于新疆尽职调查的声明”称，H&M集团对来自民间社会组织的报告和媒体的报道“深表关注”，\n"
					+ "其中包括对新疆维吾尔自治区少数民族“强迫劳动”和“宗教歧视”的指控。\n"
					+ "声明表示，H&M不与位于新疆的任何服装制造工厂合作，也不从该地区采购产品/原材料。面对中国网友的怒火，\n"
					+ "H&M集团瑞典总部24日表示，“无法在电话中做出回应，将在查看邮件后回复”。而“H&M中国”微博账号24日晚发表声明称，\n"
					+ "H&M集团一贯秉持公开透明的原则管理我们的全球供应链，并不代表任何政治立场\n";
			//将写入的字符串转为字节数组
			byte[] b = str.getBytes();
			out.write(b);
			if(desc.exists() && desc.length()>0){
				System.out.println("写入陈宫...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(out, null);
		}
	}
	
	
	//字节流文件复制
	public static void fileCopy(String srcaPath,String descPath){
		//构建源文件对象，读取该文件的内容
		File src=new File(srcaPath);
		//构建一个被复制的文件,写入的目标文件
		File desc=new File(descPath);
		//构建输入流对象
		InputStream in=null;
		//构建输出流对象
		OutputStream out=null;
		//定义一个放字节的数组
		byte[] b=new byte[1024*1024];
		int k=0;
		try {
			in=new FileInputStream(src);
			out=new FileOutputStream(desc);
			while((k=in.read(b))!=-1){
				System.out.println(k);
				out.write(b,0,k);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(out, in);
		}
	}
	
	public static void close(OutputStream out,InputStream in){
		try {
			if(out!=null){
				out.close();
			}
			if(in!=null){
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//readFile("E:\\Test.java");
		//writeFile("E:\\xuwei.txt");
		//fileCopy("D:\\用户目录\\我的视频\\java39.mp4","E:\\a.mp4");
	}

}

