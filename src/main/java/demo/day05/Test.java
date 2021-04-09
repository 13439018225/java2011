package demo.day05;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {
	
	private IDao dao;
	
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	
	public static void main(String[] args) {
		//获取连接
		//IDao dao=new OracelDao();
		//IDao dao=new MysqlDao();
		//dao.getCon();
		
		//读取配置文件
		Properties pro=new Properties();
		try {
			pro.load(new FileInputStream("E:\\rcieWorkSpace\\java2011\\src\\main\\java\\demo\\day05\\bean.properties"));
			//读取文件属性值
			String classname=pro.getProperty("classname");
			//通过类名称构建字节码文件对象 Class类型的对象
			Class clazz=Class.forName(classname);
			//反射创建对象
			IDao dao=(IDao)clazz.newInstance();
			dao.getCon();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
