package main.java.demo.day04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 字节缓存流
 * @author Administrator
 *
 */
public class MyBufferdIo {
	
	

	//读文件
	public static void readBufferFile(String srcfilePath){
		File file=new File(srcfilePath);
		BufferedInputStream in=null;
		byte[] b=new byte[1024];
		int k=0;
		try {
			in=new BufferedInputStream(new FileInputStream(file));
			while((k=in.read(b))!=-1){
				String s=new String(b,0,k);
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,in);
		}
	}
	
	//写文件
	public static void writeBufferFile(String descfilePath){
		File file=new File(descfilePath);
		BufferedOutputStream out=null;
		try {
			//new FileOutputStream(file,true) true 表示可以追加内容
			out=new BufferedOutputStream(new FileOutputStream(file,true));
			String str="【环球时报记者 李司坤 邢晓婧 李萌 高雷】“一边造谣抵制新疆棉花，一边又想在中国赚钱？\n"
					+ "痴心妄想！”24日，瑞典服装品牌H&M发表在官网上的一份声明在微博上广泛传播，引发中国网友愤怒。\n"
					+ "这份名为“H&M集团关于新疆尽职调查的声明”称，H&M集团对来自民间社会组织的报告和媒体的报道“深表关注”，\n"
					+ "其中包括对新疆维吾尔自治区少数民族“强迫劳动”和“宗教歧视”的指控。\n"
					+ "声明表示，H&M不与位于新疆的任何服装制造工厂合作，也不从该地区采购产品/原材料。面对中国网友的怒火，\n"
					+ "H&M集团瑞典总部24日表示，“无法在电话中做出回应，将在查看邮件后回复”。而“H&M中国”微博账号24日晚发表声明称，\n"
					+ "H&M集团一贯秉持公开透明的原则管理我们的全球供应链，并不代表任何政治立场\n";
			out.write(str.getBytes());
			//强制写入文件
			//out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(out,null);
		}
	}
	
	//文件的复制
	public static void bufferdFileCopy(String srcFile,String descFile){
		BufferedInputStream in=null;
		BufferedOutputStream out=null;
		byte[] b=new byte[1024*1024*1024];
		int k=0;
		try {
			in =new BufferedInputStream(new FileInputStream(new File(srcFile)));
			out=new BufferedOutputStream(new FileOutputStream(new File(descFile)));
			while((k=in.read(b))!=-1){
				out.write(b, 0, k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(out,in);
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
		//writeBufferFile("E:\\xw.java");
		bufferdFileCopy("E:\\worksoft\\VMware10.rar","E:\\a.rar");
	}
}
